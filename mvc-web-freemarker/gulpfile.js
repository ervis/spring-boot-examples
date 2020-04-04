// goals
// reload browser automatically when file is saved
// use pre-processors sass
// run linter
// run tests
const gulp = require('gulp');
const browserSync = require('browser-sync').create();
const sass = require('gulp-sass');

const dev = {
    src: "src/main/resources",
    freemarker: "src/main/resources/templates",
    scss: "src/main/resources/static/scss",
    js: "src/main/resources/static/js",
    images: "src/main/resources/static/images",
    fonts: "src/main/resources/static/fonts",
};

const dist = {
    src: "src/main/resources",
    freemarker: "src/main/resources/templates",
    scss: "src/main/resources/static/scss",
    js: "src/main/resources/static/js",
    images: "src/main/resources/static/images",
    fonts: "src/main/resources/static/fonts",
};

gulp.task('serve', () => {
    browserSync.init({
        server: {
            baseDir: paths.src
        },
    });

    gulp.watch(`${dev.scss}/**/*.scss`, ['sass']);
    gulp.watch(`${dev.freemarker}/**/*.ftlh`, ['freemarker']);
});

gulp.task('browserSync', function () {
    browserSync.init({
        server: {
            baseDir: 'src/main/resources'
        },
    })
});

// compile sass into css and auto-inject into browser
gulp.task('sass', () => {
    return gulp.src("src/main/resources/static/scss/**/*.scss")
        .pipe(sass())
        .pipe(gulp.dest("build/classes/main/static/css"))
        .pipe(browserSync.stream());
});

gulp.task('freemarker', () => {
    return gulp.src("src/main/resources/static/scss/**/*.scss")
        .pipe(sass())
        .pipe(gulp.dest("src/main/resources/static/css"))
        .pipe(browserSync.stream());
});