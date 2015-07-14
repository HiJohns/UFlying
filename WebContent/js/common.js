UF = {
	stores: {},
	utils: {
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
		},
		makeMappingTables: function makeMappingTable(mappings, namespace) {
		    function _solveBase(key) {
		        var fld = newMappings[key];

//		        if (fld.hasOwnProperty('__base') && !_.isObject(fld.__base)) {
//		            fld.__base = Ext.isString(fld.__base) ? _solveBase(namespace[fld.__base]) : common;
//		            Ext.applyIf(fld, fld.__base);
//		        }

		        return fld;
		    }

		    namespace = namespace || mappings.__namespace;
		    var newMappings = {};
		    var common = mappings.__common || {};

		    for (var fldName in mappings) {
		        if (!mappings.hasOwnProperty(fldName) || fldName == '__namespace' || fldName == '__common') continue;
		        newMappings[namespace.hasOwnProperty(fldName) ? namespace[fldName] : fldName] = mappings[fldName];
		    }

		    for (var key in newMappings) {
		        if (_.isObject(newMappings[key])) _solveBase(key);
		    }

		    return newMappings;
		},
		loadSelect: function () {
			if ($(this).prop('tagName') != 'SELECT') return;
			
			var store = UF.stores[$(this).attr('data-store')];
			if (!_.isObject(store)) {
				console.log(this, 'Store not found.');
				return;
			}
			
			var textField = $(this).attr('data-textField') || 'name';
			var valueField = $(this).attr('data-valueField') || 'id';
			
			$(this).find('option:not([disabled])').remove();
			var _self = this;
			_.each(store, function (record) {
				$(_self).append($('<option>', { text: record[textField], value: record[valueField] }));
			});
			
			$(this).val('');
		},
		getHint: function () {
			return '请填写' + ($(this).attr('data-name') || '该字段');
		},
		validate: function () {
			val = $(this).val();
			if ($(this).is(':enabled:visible') && (val == null || (_.isString(val) && val.length == 0))) {
				$(this).addClass('invalid').attr('title', UF.utils.getHint.call(this));
			}
			else {
				$(this).removeClass('invalid');
			}
		},
		autoValidateForm: function () {
			if (!_.isUndefined($(this).attr('data-autoValidate'))) return;
			$(this).attr('data-autoValidate', 'true').attr('novalidate', 'true');
			$(this).find('input[required]').change(UF.utils.validate).keyup(UF.utils.validate).each(UF.utils.validate);
			$(this).find('select[required]').change(UF.utils.validate).each(UF.utils.validate);
			$(this).submit(function () {
				$(this).find('select:visible[required]').each(UF.utils.validate);
				$(this).find('input:visible[required]').each(UF.utils.validate);
				var stack = [];
				$(this).find('.invalid').each(function (index) {
					stack.push((index+1) + '. ' + UF.utils.getHint.call(this));
				});
				
				if (stack.length == 0) {
					$(this).find('input[type="submit"]').prop('disabled', true);
					return true;
				}
				
				alert('以下项目需要修正：\n' + stack.join('\n'));
				return false;
			});
		},
		renderModel: function (model) {
			function getValue(name) {
				return model.hasOwnProperty(name) ? model[name] : '';
			}
			
			function getValueEx(name) {
		    	var stack = name.split('+');
		    	for (var i = 0; i < stack.length; i++) {
		    		var n = stack[i];
		    		var v = getValue(n);
		    		if (_textRenderers.hasOwnProperty(n)) {
		    			var renderer = _textRenderers[n];
		    			if (_.isFunction(renderer)) {
		    				v = renderer(v, model);
		    			}
		    			else if (_.isObject(renderer) || _.isArray(renderer)) {
		    				v = renderer[v];
		    			}
		    		}
		    		stack[i] = v;
		    	}
		    	
		    	return stack.join('');
			}
			
		    $('*[name]').each(function () {
		    	var name = $(this).attr('name');
		    	
		        switch ($(this).prop('tagName').toLowerCase()) {
			        case 'input':
			        	switch ($(this).attr('type')) {
			        	case 'radio':
			                $(this).prop('checked', $(this).val() == getValue(name));
			                break;
			        	case 'checkbox':
			        		$(this).prop('checked', getValue(name));
			        		break;
			            default:
			                $(this).val(getValue(name));
			            }
			        	if ($(this).is(':visible')) UF.utils.validate.call(this);
			            break;
			        case 'select':
		                $(this).val(getValue(name));
			        	if ($(this).is(':visible')) UF.utils.validate.call(this);
			        	break;
			        case 'span':
			        case 'div':
			            this.innerHTML = getValueEx(name);
			            break;
			        case 'img':
			        	$(this).attr('src', getValue(name));
		        }
		    });
		}
	},
	enums: {
	    status: {
	        base: 0,
	        authenticating: 1,
	        authenticated: 2,
	        contracting: 3,
	        contracted: 4
	    }
	}
}

_textRenderers = {
	sex: {
		0: '女',
		1: '男',
		2: '保密'
	}, 
	uid: function (uid, model){
        if (_.isString(uid) && uid.match(/^[A-Za-z]/) != null) return uid;
		var result = uid.toString();
		while (result.length < 10) result = '0' + result;
		return 'G' + result;
	}, 
	status: {
		0: '普通用户',
		1: '待认证',
		2: '认证用户',
		3: '待商签',
		4: '商签用户'
	}
};

$(document).ready(function () {
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
    	if (_.isObject(model)) UF.utils.renderModel(model);
    }, 100);
    
    $('form').each(UF.utils.autoValidateForm);
});
