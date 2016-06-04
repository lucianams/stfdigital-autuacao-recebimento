'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var $ = require('gulp-load-plugins')({
		pattern: ['gulp-*', 'uglify-save-license', 'del']
});

var tsProject = $.typescript.createProject(path.join(conf.paths.src, 'tsconfig.json'));
var allTypeScript = path.join(conf.paths.app, '**/*.ts');
var libraryTypeScript = path.join(conf.paths.src, 'typings/main/**/*.d.ts');
var tsOutputPath = conf.paths.tmp;
var tsGenFiles = path.join(conf.paths.tmp, '**/*.js');
var tsGenMapFiles = path.join(conf.paths.tmp, '**/*.js.map');

var tsProjectE2E = $.typescript.createProject(path.join(conf.paths.e2e, 'tsconfig.json'));
var allTypeScriptE2E = path.join(conf.paths.e2e, '**/*.ts');
var libraryTypeScriptE2E = path.join(conf.paths.e2e, 'typings/main/**/*.d.ts');
var tsOutputPathE2E = conf.paths.e2e;
var tsGenFilesE2E = path.join(conf.paths.e2e, '**/*.js');

var tsProjectUnit = $.typescript.createProject(path.join(conf.paths.unit, 'tsconfig.json'));
var allTypeScriptUnit = path.join(conf.paths.unit, '**/*.ts');
var libraryTypeScriptUnit = path.join(conf.paths.unit, 'typings/main/**/*.d.ts');
var tsOutputPathUnit = conf.paths.unit;
var tsGenFilesUnit = path.join(conf.paths.unit, '**/*.js');

/**
 * Install all typings files
 */
gulp.task('install-typings', function() {
    return gulp.src('typings.json', {cwd : conf.paths.src})
        .pipe($.typings());
});

/**
 * Install all e2e typings files
 */
gulp.task('install-typings:e2e', function() {
    return gulp.src('typings.json', {cwd : conf.paths.e2e})
        .pipe($.typings());
});

/**
 * Install all unit typings files
 */
gulp.task('install-typings:unit', function() {
    return gulp.src('typings.json', {cwd : conf.paths.unit})
        .pipe($.typings());
});

/**
 * Lint all custom TypeScript files.
 */
gulp.task('ts-lint', ['install-typings'], function() {
    return gulp.src(allTypeScript)
    			.pipe($.tslint())
    			.pipe($.tslint.report('prose'));
});

/**
 * Lint all custom TypeScript files.
 */
gulp.task('ts-lint:e2e', ['install-typings:e2e'], function() {
    return gulp.src(allTypeScriptE2E)
    			.pipe($.tslint())
    			.pipe($.tslint.report('prose'));
});

/**
 * Lint all custom TypeScript files.
 */
gulp.task('ts-lint:unit', ['install-typings:unit'], function() {
    return gulp.src(allTypeScriptUnit)
    			.pipe($.tslint())
    			.pipe($.tslint.report('prose'));
});

/**
 * Compile TypeScript and include references to library and app .d.ts files.
 */
gulp.task('compile-ts:e2e', ['ts-lint:e2e'], function() {
    return gulp.src([allTypeScriptE2E, libraryTypeScriptE2E])
        .pipe($.typescript(tsProjectE2E))
        .pipe($.ngAnnotate())
        .pipe(gulp.dest(tsOutputPathE2E));
});

/**
 * Compile TypeScript and include references to library and app .d.ts files.
 */
gulp.task('compile-ts:unit', ['ts-lint:unit'], function() {
    return gulp.src([allTypeScriptUnit, libraryTypeScriptUnit])
        .pipe($.typescript(tsProjectUnit))
        .pipe($.ngAnnotate())
        .pipe(gulp.dest(tsOutputPathUnit));
});

/**
 * Remove all generated JavaScript files from TypeScript compilation.
 */
gulp.task('clean-ts', function(cb) {
	$.del([ tsGenFiles, tsGenMapFiles, tsGenFilesE2E ]).then(function () {
        cb();
    }, function (reason) {
        cb("Failed to delete files " + reason);
    });
});
