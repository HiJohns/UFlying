UF.business.Form = (function () {
	var zeroBase = { 
		NUMBER: true
	};
	
	function is(obj, label) {
		return $(obj).is('[' + label + ']');
	}

    var labels = UF.base.FormLabels;
	return {
		init: function () {
		    var prototypes = UF.base.Prototypes;
		    $('[' + labels.prototype + ']').each(function () {
		    	var prototypeName = $(this).attr(labels.prototype);
		    	if (!prototypes.hasOwnProperty(prototypeName)) return;
		    	
		    	var prototype = prototypes[prototypeName];
		    	for (var field in prototype) {
		    		if (is(this, field)) continue;
		    		$(this).attr(field, prototype[field]);
		    	}
		    });
		    
		    $('input').attr(labels.noValidate, 'true');
		    $('select').attr(labels.noValidate, 'true');
		    $('form').each(UF.business.Form.autoValidateForm);

		    var imgTmpl = _.template('url(<%=contextPath%>/img/<%=dataIcon%>.png)');
		    $('*[' + labels.icon + ']').each(function () {
		        $(this).css({
		            backgroundImage: imgTmpl({ contextPath: contextPath, dataIcon: $(this).attr(labels.icon) })
		        });
		    });
		    
	    	if (_.isObject(model)) UF.business.Form.renderModel(model);
	    	$('input').removeAttr(labels.noValidate);
	    	$('select').removeAttr(labels.noValidate);
		},
		loadSelect: function () {
			if ($(this).prop('tagName') != 'SELECT') return;
			
			var store = UF.Stores[$(this).attr('data-store')];
			if (!_.isObject(store)) {
				console.log(this, 'Store not found.');
				return;
			}
			
			var textField = $(this).attr(labels.textField) || 'name';
			var valueField = $(this).attr(labels.valueField) || 'id';
			
			$(this).find('option:not([disabled])').remove();
			var _self = this;
			_.each(store, function (record) {
				$(_self).append($('<option>', { text: record[textField], value: record[valueField] }));
			});
			
			$(this).val('');
		},
		validate: function () {
			function clearError() {
				$(this).removeClass('invalid').attr('title', '').siblings('.error').remove();
			}
			
			function addError(obj, msg) {
				$(obj).each(clearError);
				if (msg != null) {
					$(obj).addClass('invalid').attr('title', msg);
					$('<small>').addClass('error').html(msg).appendTo($(obj).parent());
				}
			}
			
			function release() {
				$(this).removeClass('onHold').prop('disabled', false).siblings('.message').remove();
			}
			
			function putOnHold(obj, msg) {
				$(obj).addClass('onHold').prop('disabled', true);
				$('<small>').addClass('message').html(msg).appendTo($(obj).parent());
			}
			
			var val = $(this).val();
			
			if (val === $(this).attr(labels.validated)) return;
			
			$(this).each(clearError);
			if (!$(this).is(':enabled:visible') || is(this, labels.noValidate)) return;
					
			var msg = null;
			if ($(this).is('[required]') && (val == null || (_.isString(val) && val.length == 0))) {
				msg = $(this).attr('msg-empty') || '请填写本字段';
			}
			
			if (msg == null && (is(this, labels.regex) || is(this, labels.regexName))) {
				var regexString = $(this).attr(labels.regex) || UF.base.RegularExpressions[$(this).attr(labels.regexName)];
				if (_.isString(val) && !(new RegExp(regexString, 'i').test(val))) {
					msg = $(this).attr(labels.regexMessage);
				}
			}
			
			if (msg == null && is(this, labels.match)) {
				var matchField = $('[name="' + $(this).attr(labels.match) + '"]');
				var errorForMatch = null;
				
				if (val != matchField.val() && matchField.is(':visible:enabled')) {
					msg = $(this).attr(labels.matchMessage);
					if (matchField.val() != '') errorForMatch = $(matchField).attr(labels.matchMessage);
				}
				addError(matchField, errorForMatch);
			}
			
			addError(this, msg);			
			
			if (msg == null && is(this, labels.remote)) {
				var msgRemote = $(this).attr(labels.remoteMessage);
				var _self = this;
				
				putOnHold(_self, $(this).attr(labels.remoteCheckingMessage));
				UF.Remote[$(this).attr(labels.remote)](val, _self, function (isValid, errMsg) { 
					$(_self).each(release);
                    if (_.isString(errMsg)) errMsg = '验证出错，错误信息：' + errMsg;
					addError(_self, isValid ? null : (errMsg || msgRemote)); 
					$(_self).attr(labels.validated, val);
				});
			}
			else {
				$(this).attr(labels.validated, val);
			}
		},
		autoValidateForm: function () {
			if (!_.isUndefined($(this).attr(labels.autoValidate))) return;
			$(this).attr(labels.autoValidate, 'true').attr('novalidate', 'true');
			
			$(this).find('input').prop('disabled', false);
			$(this).find('select').prop('disabled', false);
			$(this).find('input[data-disabled]').prop('disabled', true);
			$(this).find('select[data-disabled]').prop('disabled', true);
			
			$(this).find('input').click(UF.business.Form.validate).keyup(UF.business.Form.validate);
			$(this).find('select').change(UF.business.Form.validate);
			$(this).submit(function () {
				if ($(this).find('.onHold').length > 0) return false;
				$(this).find(':hidden').removeClass('invalid');
				$(this).find('select:visible').each(UF.business.Form.validate);
				$(this).find('input:visible').each(UF.business.Form.validate);
				var stack = [];
				$(this).find('.invalid').each(function (index) {
					stack.push((index+1) + '. ' + $(this).attr('title'));
				});
				
				if (stack.length == 0) {
					$(this).find('input[type="submit"]').prop('disabled', false);
					$(this).find('input:hidden:not([type=hidden])').each(function () {
						$(this).val(zeroBase.hasOwnProperty($(this).attr('type')) ? 0 : null);
					})
					$(this).find('select:hidden').val(null);
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
		    		if (UF.base.Renderers.hasOwnProperty(n)) {
		    			var renderer = UF.base.Renderers[n];
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
			
			if (_.isString(model.message) && model.message.length > 0) {
				$('.portal.alertBox').show();
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
			        	if ($(this).is(':visible')) UF.business.Form.validate.call(this);
			            break;
			        case 'select':
			        	console.log(UF.Stores);
			        	console.log(getValue(name), name);
		                $(this).each(UF.business.Form.loadSelect);
		                $(this).val(getValue(name));
			        	if ($(this).is(':visible')) UF.business.Form.validate.call(this);
			        	break;
			        case 'span':
			        case 'div':
			        case 'small':
			            this.innerHTML = getValueEx(name);
			            break;
			        case 'img':
			        	$(this).attr('src', getValue(name));
		        }
		    });
		}
	}
})();
