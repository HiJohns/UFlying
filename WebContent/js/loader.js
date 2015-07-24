UF = {
	Stores: {},
	Remote: {},
	ns: function (namespace) {
		var stack = namespace.split('.');
		if (stack.length == 0) return;
		
		var root = stack.shift();
		if (root.length == 0) return;
		
		if (eval('_.isUndefined(' + root  + ')')) {
			eval(root + ' = {}');
		}
		
		eval('var layer = ' + root);
		
		while (stack.length > 0) {
			var name = stack.shift();
			if (!layer.hasOwnProperty(name)) layer[name] = {};
			layer = layer[name];
		}
	}
}

$(document).ready(function () {
	var scripts = {
		base: [
               "Enums",
               "RegularExpressions",
               "Cities",
               "Renderers",
               "Utils"
               ],
	    page: [
               	location.href.split('/').pop()
               ]
	};
	
	function loadScripts(name) {
		var succeeded = 0;
		var finished = 0;
		var success = null;
		var failure = function () { console.log('载入' + name + '失败。'); };
		var result = {
			done: function (callback) {
				success = callback;
				return result;
			},
			fail: function (callback) {
				failure = callback;
				return result;
			}
		};
		
		function initAllModules() {
			_.each(scripts[name], function (moduleName) {
				if (_.isObject(UF[name][moduleName]) && _.isFunction(UF[name][moduleName].init)) {
					UF[name][moduleName].init();
				}
			});
		}
		
		UF.ns('UF.' + name);
		_.each(scripts[name], function (moduleName) {
			$.getScript(contextPath + '/js/' + name + '/' + moduleName + '.js')
				.done(function () {
					finished++;
					if (++succeeded == scripts[name].length && _.isFunction(success)) {
						initAllModules();
						success();
					}
				})
				.fail(function () {
					console.log('Failure', arguments);
					if (++finished == scripts[name].length && _.isFunction(failure)) {
						failure();
					}
				})
		});
		
		return result;
	}
	
	function ready() {
	    $('img').each(function () {
	        var src = $(this).attr('src');
	        if (typeof src != 'string' || src.match(/^\//) != null) return;
	        $(this).attr('src', contextPath + '/' + src);
	    });
	    
	    $('.back').click(function () {
	    	history.go(-1);
	    });
	    
	    if (_.isObject(UF.page[pageObjName])) UF.page[pageObjName].init();
	}
	
	function loadDependencies() {
		if (_.isObject(UF.page[pageObjName]) && _.isArray(UF.page[pageObjName].dependencies)) {
			scripts.business = UF.page[pageObjName].dependencies;
			loadScripts('business').done(ready);
		}
		else {
			ready();
		}
	}
	
    function toBigCamel(s) {
    	return _.isString(s) ? s[0].toUpperCase() + s.substr(1) : '';
    }
    
    var pageObjName = scripts.page[0].replace(/([a-zA-Z0-9]+)(_([a-zA-Z0-9]+))?/, function (match, group1, group2, group3) {
  	  return toBigCamel(group1) + toBigCamel(group3);
    });
  
	loadScripts('base').done(function () {
		loadScripts('page').done(loadDependencies).fail(ready);
	})
});
