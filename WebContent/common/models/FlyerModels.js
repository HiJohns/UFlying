UfCommon.factory('modFlyermodels', function (misThread) {
	var _cache = null;
	return {
		load: function (callback) {
			if (_cache !== null) return callback(_cache);
			_cache = [
			          {
			        	  mode: '<mode code>', 
			        	  vendor: '<vendor code>',
			        	  weight: '<weight>',
			        	  title: '<title>'
			          }, 
			          {
			        	  mode: '<mode code 1>',
			        	  vendor: '<vendor code1>',
			        	  weight: '<weight1>',
			        	  title: '<title1>'
			          }
			          ]
			
			callback(_cache);
		}
	}
})