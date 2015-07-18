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
               "Enums.js",
               "RegularExpressions.js",
               "Cities.js",
               "Utils.js"
               ],
	
        framework: [
	               "Renderers.js",
	               "Forms.js"
	               ],
	    page: [
               	location.href.split('/').pop() + '.js'
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
		_.each(scripts[name], function (url) {
			$.getScript(contextPath + '/js/' + name + '/' + url)
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

	    _.delay(function () {
	    	if (_.isObject(model)) UF.framework.Form.renderModel(model);
	    	$('input').removeAttr('data-novalidate');
	    	$('input[type=submit]').removeAttr('disabled');
	    	$('select').removeAttr('data-novalidate');
	    }, 100);
	    
	    $('input').attr('data-novalidate', 'true');
	    $('select').attr('data-novalidate', 'true');
	    $('form').each(UF.framework.Form.autoValidateForm);
	}
	
	loadScripts('base').done(function () {
		loadScripts('framework').done(function () {
			loadScripts('page').done(ready).fail(ready);
		})
	})
});
