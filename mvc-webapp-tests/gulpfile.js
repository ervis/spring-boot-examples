const gulp = require('gulp');
const babel = require('gulp-babel');
const sass = require('gulp-sass');
const sassLint = require('gulp-sass-lint');
const eslint = require('gulp-eslint');
const concat = require('gulp-concat');
const sourcemaps = require('gulp-sourcemaps');
const browserSync = require('browser-sync').create();

// TODO: minify and uglify
const minify = require('gulp-babel-minify');
const uglify = require('gulp-uglify');
const rename = require("gulp-rename");

// is this one used?
const watch = require('gulp-watch');

const server = 'localhost:8081';

const paths = {
    resources: 'src/main/resources'
};

const styles = {
    file: 'main.css',
    input: 'src/scss',
    output: 'src/main/resources/static/assets/app/css'
};

const js = {
    fileLocation: 'src/main/resources/static/assets/app/js/main.js',
    file: 'main.js',
    input: 'src/js',
    output: 'src/main/resources/static/assets/app/js'
};

const build = {
    // replace with build/resources/main/ for netBeans
    // or classpath: 'out/production/resources/'
    // this is the only one that works on IDEA
    classpath: 'build/src/main'
};

const task = {
    babel: 'babel',
    build: 'build',
    minifyjs: 'minifyjs',
    default: 'default',
    sass: 'sass',
    sassLinter: 'sass-lint',
    eslint: 'js-lint',
    watch: 'watch',
    copyAndReload: 'copy-resources-and-reload',
    copyResources: 'copy-resources',
};

gulp.task(task.watch, () => {
    browserSync.init({proxy: server});
    gulp.watch([`${styles.input}/**`], gulp.series(task.sass));
    gulp.watch([`${js.input}/**`], gulp.series(task.babel));
    gulp.watch([`${paths.resources}/**`], gulp.series(task.copyAndReload));
});

gulp.task(task.sass, () => {
    return gulp.src(`${styles.input}/**/*.s+(a|c)ss`)
        .pipe(sourcemaps.init())
        .pipe(sass())
        .pipe(concat(styles.file))
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest(styles.output));
});

gulp.task(task.babel, () => {
    return gulp.src(`${js.input}/**/*.js`)
        .pipe(sourcemaps.init())
        .pipe(babel())
        .pipe(concat(js.file))
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest(js.output));
});

// used for code quality
gulp.task(task.sassLinter, () => {
    return gulp.src(`${styles.input}/**/*.s+(a|c)ss`)
        .pipe(sassLint())
        .pipe(sassLint.format())
        .pipe(sassLint.failOnError());
});

// used for code quality
gulp.task(task.eslint, () => {
    return gulp.src(`${js.input}/**/*.js`)
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failOnError());
});

gulp.task(task.copyResources, () => gulp.src([`${paths.resources}/**`]).pipe(gulp.dest(build.classpath)));

gulp.task(task.copyAndReload, gulp.series(task.copyResources, reload));
gulp.task(task.build, gulp.series(task.copyResources,));
gulp.task(task.default, gulp.series(task.watch));

function reload(done) {
    browserSync.reload();
    done();
}