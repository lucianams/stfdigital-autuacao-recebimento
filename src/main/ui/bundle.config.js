var lazypipe = require('lazypipe');
var path = require('path');
var typescript = require('gulp-typescript');
var ngAnnotate = require('gulp-ng-annotate');
var embedTemplates = require('gulp-angular-embed-templates');
var moduleNameInjector = require('gulp-systemjs-module-name-injector');

var conf = require('../../../gulp/conf');

var createTsProject = function() {
	return typescript.createProject(path.join(conf.paths.src, 'tsconfig.json'));
};
var libraryTypeScript = path.join(conf.paths.src, 'typings/main/**/*.d.ts');

module.exports = {
  bundle: {
    'peticoes-fisicas': {
      scripts: [path.join(conf.paths.app, 'peticoes-fisicas.ts'),
                path.join(conf.paths.app, 'peticoes-fisicas/**/*.ts'), libraryTypeScript],
      options: {
    	  rev: false,
    	  transforms: {
              scripts: lazypipe()
              	.pipe(typescript, createTsProject())
              	.pipe(moduleNameInjector, {rootDir: 'src/main/ui/app/', prefix: 'recebimento/'})
              	.pipe(ngAnnotate)
              	.pipe(embedTemplates, {
              		skipErrors: true, 
              		minimize: {
              			empty : true,
              			spare : true,
              			quotes: true
              		}
              	})
          }
      }
    },
    'preautuacao': {
      scripts: [path.join(conf.paths.app, 'preautuacao.ts'),
                path.join(conf.paths.app, 'preautuacao/**/*.ts'),
                path.join(conf.paths.app, 'services.ts'),
                path.join(conf.paths.app, 'services/**/*.ts'), libraryTypeScript],
      options: {
    	  rev: false,
    	  transforms: {
              scripts: lazypipe()
              	.pipe(typescript, createTsProject())
              	.pipe(moduleNameInjector, {rootDir: 'src/main/ui/app/', prefix: 'recebimento/'})
              	.pipe(ngAnnotate)
              	.pipe(embedTemplates, {
              		skipErrors: true, 
              		minimize: {
              			empty : true,
              			spare : true,
              			quotes: true
              		}
              	})
          }
      }
    },
    'preautuacao-recursal': {
        scripts: [path.join(conf.paths.app, 'preautuacao-recursal.ts'),
                  path.join(conf.paths.app, 'preautuacao/recursal/**/*.ts'),
                  path.join(conf.paths.app, 'services.ts'),
                  path.join(conf.paths.app, 'services/**/*.ts'), libraryTypeScript],
        options: {
      	  rev: false,
      	  transforms: {
                scripts: lazypipe()
                	.pipe(typescript, createTsProject())
                	.pipe(moduleNameInjector, {rootDir: 'src/main/ui/app/', prefix: 'recebimento/'})
                	.pipe(ngAnnotate)
                	.pipe(embedTemplates, {
                		skipErrors: true, 
                		minimize: {
                			empty : true,
                			spare : true,
                			quotes: true
                		}
                	})
            }
        }
      },
      'services': {
          scripts: [path.join(conf.paths.app, 'services.ts'),
                    path.join(conf.paths.app, 'services/**/*.ts'), libraryTypeScript],
          options: {
        	  rev: false,
        	  transforms: {
                  scripts: lazypipe()
                  	.pipe(typescript, createTsProject())
                  	.pipe(moduleNameInjector, {rootDir: 'src/main/ui/app/', prefix: 'recebimento/'})
                  	.pipe(ngAnnotate)
                  	.pipe(embedTemplates, {
                  		skipErrors: true, 
                  		minimize: {
                  			empty : true,
                  			spare : true,
                  			quotes: true
                  		}
                  	})
              }
          }
        },
        'preparar-oficio-devolucao': {
            scripts: [path.join(conf.paths.app, 'preparar-oficio-devolucao.ts'),
                      path.join(conf.paths.app, 'preparar-oficio-devolucao/**/*.ts'), libraryTypeScript],
            options: {
          	  rev: false,
          	  transforms: {
                    scripts: lazypipe()
                    	.pipe(typescript, createTsProject())
                    	.pipe(moduleNameInjector, {rootDir: 'src/main/ui/app/', prefix: 'recebimento/'})
                    	.pipe(ngAnnotate)
                    	.pipe(embedTemplates, {
                    		skipErrors: true, 
                    		minimize: {
                    			empty : true,
                    			spare : true,
                    			quotes: true
                    		}
                    	})
                }
            }
          },
          'devolucao-assinatura': {
              scripts: [path.join(conf.paths.app, 'devolucao-assinatura.ts'),
                        path.join(conf.paths.app, 'devolucao-assinatura/**/*.ts'),
                        path.join(conf.paths.app, 'services.ts'),
                        path.join(conf.paths.app, 'services/**/*.ts'), libraryTypeScript],
              options: {
            	  rev: false,
            	  transforms: {
                      scripts: lazypipe()
                      	.pipe(typescript, createTsProject())
                      	.pipe(moduleNameInjector, {rootDir: 'src/main/ui/app/', prefix: 'recebimento/'})
                      	.pipe(ngAnnotate)
                      	.pipe(embedTemplates, {
                      		skipErrors: true, 
                      		minimize: {
                      			empty : true,
                      			spare : true,
                      			quotes: true
                      		}
                      	})
                  }
              }
            }
  },
  copy: [{
	  src : path.join(conf.paths.app, '**/*.json'),
      base: conf.paths.app
  }]
};