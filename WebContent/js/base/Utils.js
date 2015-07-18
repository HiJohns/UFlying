UF.base.Utils = {
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
	}
}
