const gulp = require('gulp');
const watch = require('gulp-watch');
const sass = require('gulp-sass');
const sassLint = require('gulp-sass-lint');
const concat = require('gulp-concat');
const sourcemaps = require('gulp-sourcemaps');
const browserSync = require('browser-sync').create();

const paths = {
    resources: 'src/main/resources',
    css: 'src/main/resources/static/assets/app/css',
    scss: 'src/scss'
};
const styles = {
    file: 'main.css'
};
const build = {
    classpath: 'build/src/main'
};
gulp.task('watch', () => {
    browserSync.init({proxy: 'localhost:8081',});
    gulp.watch([`${paths.scss}/**`], gulp.series('sass'));
    gulp.watch([`${paths.resources}/**`], gulp.series('copy-resources-and-reload'));
});

gulp.task('sass', () => {
    return gulp.src(`${paths.scss}/**/*.s+(a|c)ss`)
        .pipe(sassLint())
        .pipe(sassLint.format())
        .pipe(sourcemaps.init())
        .pipe(sass())
        .pipe(concat(styles.file))
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest(paths.css));
});

// used for code quality
gulp.task('sass-lint', () => {
    return gulp.src(`${paths.scss}/**/*.s+(a|c)ss`)
        .pipe(sassLint())
        .pipe(sassLint.format())
        .pipe(sassLint.failOnError());
});

//replace with build/resources/main/ for netBeans
//.pipe(gulp.dest('out/production/resources/'));
gulp.task('copy-resources', () => gulp.src([`${paths.resources}/**`]).pipe(gulp.dest(build.classpath)));

gulp.task('copy-resources-and-reload', gulp.series('copy-resources', reload));
gulp.task('build', gulp.series('copy-resources',));
gulp.task('default', gulp.series('watch'));

function reload(done) {
    browserSync.reload();
    done();
}