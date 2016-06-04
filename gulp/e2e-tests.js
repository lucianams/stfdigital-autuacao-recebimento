'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');

var $ = require('gulp-load-plugins')();

// Downloads the selenium webdriver
gulp.task('webdriver-update', $.protractor.webdriver_update);

gulp.task('webdriver-standalone', $.protractor.webdriver_standalone);

function runProtractor(done)
{
    var params = process.argv;
    var args = params.length > 3 ? [params[3], params[4]] : [];

    gulp.src(path.join(conf.paths.e2e, '/**/*.js'))
        .pipe($.protractor.protractor({
            configFile: path.join(conf.paths.test, '/protractor.conf.js'),
            args      : args
        }))
        .on('error', function (err) {
            // Make sure failed tests cause gulp to exit non-zero
            throw err;
        })
        .on('end', function () {
            // Close browser sync server
            browserSync.exit();
            done();
        });
}

gulp.task('test:e2e', ['protractor']);
gulp.task('protractor', ['webdriver-update', 'compile-ts:e2e'], runProtractor);
