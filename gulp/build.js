'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');
var del = require('del');

gulp.task('clean', ['clean-ts'], function() {
    return $.del([conf.paths.dist, conf.paths.tmp]);
});

gulp.task('build', ['scripts']);
