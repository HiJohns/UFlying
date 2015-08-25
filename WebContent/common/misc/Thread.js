UfCommon.factory('misThread', function () {
	function messageHandler(message) {
		var data = message.data;
		var record = handlers[data.id];
		if (!_.isArray(record)) {
			console.log('Invalid handler settings.');
			return;
		}
		
		var result = _.isString(data.result) ? $.parseJSON(data.result) : data.result;
		_.each(record, function (r) {
			var handler = data.success ? r.success : r.failure;
			if (_.isFunction(handler)) handler(result);
		});
	}
	
	function register(id, success, failure) {
		var record = { success: success, failure: failure };
		
		if (handlers.hasOwnProperty(id)) {
			handlers[id].push(record);
		}
		else {
			handlers[id] = [ record ];
		}
	}
	
	function start() {
		if (_worker !== null) return;
		_worker = new Worker('common/misc/ThreadWorker.js');
		_worker.addEventListener('message', messageHandler);
	}
	
	var handlers = {};
	
	var promise = function (id) {
		var _id = id;
		var _self = this;
		return {
			then: function (success, failure) {
				register(id, success, failure);
				return _self;
			}
		}	
	};
	
	var _worker = null;
	var _id = 0;
	return {
		stop: function () {
			_worker.terminate();
		}, 
		assign: function (method, url, data, success, failure) {
			if (_worker === null) start();
			if (_worker === null) return;
			var id = _id++;
			_worker.postMessage({
				id: id,
				method: method, 
				url: url, 
				data: data
			});
			
			return new promise(id);
		}
	}
});