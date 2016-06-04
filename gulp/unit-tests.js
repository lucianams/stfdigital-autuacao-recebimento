'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var karma = require('karma');

function runTests(singleRun, done)
{
    var reporters = ['progress'];
    var preprocessors = {};
    
    var localConfig = {
        configFile   : path.resolve(path.join(conf.paths.test, '/karma.conf.js')),
        singleRun    : singleRun,
        autoWatch    : !singleRun/*,*/
        //reporters    : reporters,
//        preprocessors: preprocessors
    };

    var server = new karma.Server(localConfig, function (failCount)
    {
        done(failCount ? new Error("Failed " + failCount + " tests.") : null);
    })
    server.start();
}

gulp.task('test:unit', ['scripts', 'compile-ts:unit'], function (done)
{
    runTests(true, done);
});

gulp.task('tdd', ['watch'], function (done)
{
    runTests(false, done);
});