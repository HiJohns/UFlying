UF = {
	stores: {},
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
		
		UF.ns('UF.' + name);
		_.each(scripts[name], function (moduleName) {
			$.getScript(contextPath + '/js/' + name + '/' + moduleName + '.js')
				.done(function (script) {
					finished++;
					if (++succeeded == scripts[name].length && _.isFunction(success)) {
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
		var imgTmpl = _.template('url(<%=contextPath%>/img/<%=dataIcon%>.png)');
	    $('*[data-icon]').each(function () {
	        $(this).css({
	            backgroundImage: imgTmpl({ contextPath: contextPath, dataIcon: $(this).attr('data-icon') })
	        });
	    });

	    $('img').each(function () {
	        var src = $(this).attr('src');
	        if (typeof src != 'string' || src.match(/^\//) != null) return;
	        $(this).attr('src', contextPath + '/' + src);
	    });
	    
	    $('button.back').click(function () {
	    	history.go(-1);
	    });
	    
	    if (UF.page.hasOwnProperty(pageObjName)) UF.page[pageObjName].init();
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
