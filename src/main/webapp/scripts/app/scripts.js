/**
 * global application name
 */
var app_name = 'metadata';

var global_route = {
		'index': {
			controller: 'IndexPageCtrl',
			templateUrl: 'scripts/model/templates/index.html',
			resolve: {
				deps: function($q, $rootScope) {
					var deferred = $q.defer();
		            var dependencies = [
		                'scripts/model/controller/IndexPageCtrl.js'
		            ];
		 
		            $script(dependencies, function() {
		                $rootScope.$apply(function() {
		                    deferred.resolve();
		                });
		            });
		 
		            return deferred.promise;
				}
			}
		},
		'objects': {
			controller: 'ObjectPageCtrl',
			templateUrl: 'scripts/model/templates/objects/objects.html',
			resolve: {
				deps: function($q, $rootScope) {
					var deferred = $q.defer();
		            var dependencies = [
		                'scripts/model/controller/ObjectPageCtrl.js'
		            ];
		 
		            $script(dependencies, function() {
		                $rootScope.$apply(function() {
		                    deferred.resolve();
		                });
		            });
		 
		            return deferred.promise;
				}
			}
		}
};

/**
 * function to be called when page loads
 * @param requestData JSON response from server
 */
function onLoad(requestData) {
	
	var app = angular.module(app_name, ['ngRoute', 'headerModule']);
	
	// for header UI
	angular.module('headerModule', []).directive('headerHtml', function() {
		return {
			restrict: 'E',
			templateUrl: 'scripts/model/templates/common/header.html',
			controller: function($scope, $headerManager) {
				$scope.header = $headerManager;
				
				$scope.toggleMenu = function(args) {
				    if (args != null && args.menu != null) {
				        var el = document.getElementById(args.menu);
				        if (el != null) {
				            if (el.className.indexOf('in') == -1) {
				                el.className += ' in';
				            } else {
				                el.className = el.className.replace( /(?:^|\s)in(?!\S)/g , '' );
				            }
				        }
				    }
				};
			},
			controllerAs: 'headerCtrl'
		};
	});
	
	/**
	 * factory used to bind data between header and body
	 * @author ssinha
	 */
	app.factory('$headerManager', function() {
		return {};
	});
	
	/**
	 * factory used to store and share application data
	 * @author ssinha
	 */
	app.factory('$dataManager', function() {

		var factory = {};
		var data = (typeof requestData != 'undefined')?requestData['data']: {};
		
		/**
		 * returns business data stored in this factory
		 * @param args:JSON 
		 * 			key: String name of view
		 * @return JSON
		 */
		factory.getModel = function(args) {
			
			if (args == null || args.key == null) {
				return null;
			} 
			 
			if (data == null) {
				data = {};
			}
			
			if (data.model == null) {
				data.model = {};
			}
			
			return data.model[args.key];
		};
		
		/**
		 * returns localized label stored in this factory
		 * @return JSON
		 */
		factory.getLabel = function() {
			if (data == null) {
				data = {};
			}
			
			if (data.labels == null) {
				data.labels = {};
			}
			
			return data.labels;
		};
		
		/**
		 * returns parameter stored in this factory
		 * @return JSON
		 */
		factory.getConfig = function() {
			if (data == null) {
				data = {};
			}
			
			if (data.config == null) {
				data.config = {};
			}
			
			return data.config;
		};

		/**
		 * set data to factory
		 * @param args JSON which contains key name and associated data
		 */
		factory.setData = function(args) {
			if (data == null) {
				data = {};
			}
			
			if (data.model == null) {
				data.model = {};
			}
			
			if (data.labels == null) {
				data.labels = {};
			}
			
			if (data.config == null) {
				data.config = {};
			}
			
			if (args.data != null) {
				if (args.data.labels != null) {
					for (var key in args.data.labels) {
						if (args.data.labels.hasOwnProperty(key)) {
							data['labels'][key] = args.data.labels[key];
						}
					}
				}
				
				if (args.data.config != null) {
					for (var key in args.data.config) {
						if (args.data.config.hasOwnProperty(key)) {
							data['config'][key] = args.data.config[key];
						}
					}
				}
				
				if (args.data.model != null) {
					for (var key in args.data.model) {
						if (args.data.model.hasOwnProperty(key)) {
							data['model'][key] = args.data.model[key];
						}
					}
				}
			}
		};

		return factory;
	});
	
	/**
	 * set angular js routing
	 * @author ssinha
	 */
	app.config(function($routeProvider, $controllerProvider, $compileProvider, $filterProvider, $provide) {
		
		app.provide = $provide;
		app.routeProvider = $routeProvider;
		app.filterProvider = $filterProvider;
		app.compileProvider = $compileProvider;
		app.controllerProvider = $controllerProvider;
		
		$routeProvider.when('/index', global_route['index'])
					  .when('/objects', global_route['objects'])
					  .otherwise(global_route['index']);
	});
	
	/**
	 * generic controller extended by all other controllers
	 * @author ssinha
	 */
	app.appCtrl = function($scope, $location, $dataManager, $http) {
		
		$scope.data = $dataManager;
		
		/**
		 * method used to navigate from one page to another
		 * @param args: JSON object
		 * 			page: String
		 */
		$scope.navigate = function(args) {
			
			if (args == null || args.page == null) {
				return '';
			}
			
			$location.path(args.page);
		};
		
		/**
		 * method used to navigate from one page to another
		 * @param args: JSON object
		 * 			obj: JSON Object to check
		 */
		$scope.isEmpty = function(args) {
			if (args == null || args.obj == null) {
				return true;
			}

			for (var key in args.obj) {
				if (obj.hasOwnProperty(key)) {
					return true;
				}
			}

			return false;
		};

		/**
		 * method used to make a server call
		 * @param args: JSON object
		 *			url - name of action to target
		 *			data - post data
		 *			onSuccessCallback - callback function on success
		 */
		$scope.doPost = function(args) {
			
			var data = '';
			if (args.data != null) {
				for (var key in args.data) {
					if (args.data.hasOwnProperty(key)) {
						data += ((data != null && data.length != 0)?'&':'') + key + '=' + args.data[key];
					}
				}
			}
			
			$http({
                method: "post",
                url: args.url,
                data: data,
                headers: {
                	'X-HTTP-RESULT':'json',
                	'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).success(function(data){
            	$scope.data.setData(data);
            	args['onSuccessCallback'](data);
            });
		};
	};
	
	app.controller('ApplicationCtrl', app.appCtrl);
}

/**
 * scripts js to load file asychrounously
 * @param name
 * @param definition
 */
(function (name, definition) {
  if (typeof module != 'undefined' && module.exports) module.exports = definition();
  else if (typeof define == 'function' && define.amd) define(definition);
  else this[name] = definition();
})('$script', function () {
  var doc = document
    , head = doc.getElementsByTagName('head')[0]
    , s = 'string'
    , f = false
    , push = 'push'
    , readyState = 'readyState'
    , onreadystatechange = 'onreadystatechange'
    , list = {}
    , ids = {}
    , delay = {}
    , scripts = {}
    , scriptpath
    , urlArgs;

  function every(ar, fn) {
    for (var i = 0, j = ar.length; i < j; ++i) if (!fn(ar[i])) return f;
    return 1;
  }
  function each(ar, fn) {
    every(ar, function (el) {
      return !fn(el);
    });
  }

  function $script(paths, idOrDone, optDone) {
    paths = paths[push] ? paths : [paths];
    var idOrDoneIsDone = idOrDone && idOrDone.call
      , done = idOrDoneIsDone ? idOrDone : optDone
      , id = idOrDoneIsDone ? paths.join('') : idOrDone
      , queue = paths.length;
    function loopFn(item) {
      return item.call ? item() : list[item];
    }
    function callback() {
      if (!--queue) {
        list[id] = 1;
        done && done();
        for (var dset in delay) {
          every(dset.split('|'), loopFn) && !each(delay[dset], loopFn) && (delay[dset] = []);
        }
      }
    }
    setTimeout(function () {
      each(paths, function loading(path, force) {
        if (path === null) return callback();
        path = !force && path.indexOf('.js') === -1 && !/^https?:\/\//.test(path) && scriptpath ? scriptpath + path + '.js' : path;
        if (scripts[path]) {
          if (id) ids[id] = 1;
          return (scripts[path] == 2) ? callback() : setTimeout(function () { loading(path, true); }, 0);
        }

        scripts[path] = 1;
        if (id) ids[id] = 1;
        create(path, callback);
      });
    }, 0);
    return $script;
  };

  function create(path, fn) {
    var el = doc.createElement('script'), loaded;
    el.onload = el.onerror = el[onreadystatechange] = function () {
      if ((el[readyState] && !(/^c|loade/.test(el[readyState]))) || loaded) return;
      el.onload = el[onreadystatechange] = null;
      loaded = 1;
      scripts[path] = 2;
      fn();
    };
    el.async = 1;
    el.src = urlArgs ? path + (path.indexOf('?') === -1 ? '?' : '&') + urlArgs : path;
    head.insertBefore(el, head.lastChild);
  };

  $script.get = create;

  $script.order = function (scripts, id, done) {
    (function callback(s) {
      s = scripts.shift();
      !scripts.length ? $script(s, id, done) : $script(s, callback);
    }());
  };

  $script.path = function (p) {
    scriptpath = p;
  };
  $script.urlArgs = function (str) {
    urlArgs = str;
  };
  $script.ready = function (deps, ready, req) {
    deps = deps[push] ? deps : [deps];
    var missing = [];
    !each(deps, function (dep) {
      list[dep] || missing[push](dep);
    }) && every(deps, function (dep) {return list[dep];}) ?
      ready() : !function (key) {
      delay[key] = delay[key] || [];
      delay[key][push](ready);
      req && req(missing);
    }(deps.join('|'));
    return $script;
  };

  $script.done = function (idOrDone) {
    $script([null], idOrDone);
  };

  return $script;
});