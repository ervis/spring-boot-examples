const gulp = require('gulp');
const watch = require('gulp-watch');
const sass = require('gulp-sass');
const concat = require('gulp-concat');
const sourcemaps = require('gulp-sourcemaps');
const browserSync = require('browser-sync').create();

gulp.task('watch', () => {
    browserSync.init({proxy: 'localhost:8081',});
    gulp.watch(['src/main/resources/**'], gulp.series('copy-resources-and-reload'));
    gulp.watch(['src/scss/**'], gulp.series('sass'));
});

gulp.task('sass', () => {
    return gulp.src('src/scss/**/*.scss')
        .pipe(sourcemaps.init())
        .pipe(sass())
        .pipe(concat('main.css'))
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest('build/classes/main/static/assets/app/css'));
});

//replace with build/resources/main/ for netBeans
//.pipe(gulp.dest('out/production/resources/'));
gulp.task('copy-resources', () => gulp.src(['src/main/resources/**']).pipe(gulp.dest('build/classes/main')));

gulp.task('copy-resources-and-reload', gulp.series('copy-resources', reload));
gulp.task('build', gulp.series('copy-resources',));
gulp.task('default', gulp.series('watch'));

function reload(done) {
    browserSync.reload();
    done();
}