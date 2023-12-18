var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

const fmt = require('./utils/dateFormatting');
const authUtils = require('./utils/authUtils');

var indexRouter = require('./routes/index');
const roomRouter = require('./routes/roomRoute');
const residentRouter = require('./routes/residentRoute');
const reservedRoomRouter = require('./routes/reservedRoomRoute');

const resApiRouter = require('./routes/api/ResidentAPIRoute');
const roomApiRouter = require('./routes/api/RoomAPIRouter');
const reservedApiRouter = require('./routes/api/ReservedRoomAPIRoute');

const i18n = require('i18n');

i18n.configure({
    locales: ['pl', 'en'], // languages available in the application. Create a separate dictionary for each of them 
    directory: path.join(__dirname, 'locales'), // path to the directory where the dictionaries are located
    objectNotation: true, // enables the use of nested keys in object notation
    cookie: 'acme-hr-lang', //the name of the cookie that our application will use to store information about the language currently selected by the user
});

//MP3
const session = require('express-session');

var app = express();

app.use(cookieParser('secret'));


//MP3
app.use(session({
    secret: 'my_secret_password',
    resave: false
}));

app.use((req, res, next) => {
    res.locals.fmt = fmt;
    next();
});

app.use(i18n.init);

app.use((req, res, next) => {
    if (!res.locals.lang) {
        const currentLang = req.cookies['acme-hr-lang'];
        res.locals.lang = currentLang;
    }
    next();
});

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

//link date formating


//app.use('/rooms/details', authUtils.permitOnlyForAdmin, roomRouter);
//app.use('/rooms/edit', authUtils.permitOnlyForAdmin, roomRouter);
//app.use('/rooms/delete', authUtils.permitOnlyForAdmin, roomRouter);

//app.use('/reservedRooms/edit', authUtils.permitOnlyForAdmin, reservedRoomRouter);

//app.use('/residents/delete', authUtils.permitOnlyForAdmin, residentRouter);

//MP3
app.use((req, res, next) => {
    const loggedUser = req.session.loggedUser;
    res.locals.loggedUser = loggedUser;
    const admin = req.session.admin;
    res.locals.admin = admin;
    if (!res.locals.loginError) {
        res.locals.loginError = undefined;
    }
    next();
});

//app.use('/rooms', authUtils.permitAuthentiacatedUser, roomRouter);
//app.use('/residents', authUtils.permitAuthentiacatedUser, residentRouter);
//app.use('/reservedRooms', authUtils.permitAuthentiacatedUser, reservedRoomRouter);

//app.use('/residents/details', authUtils.permitAuthentiacatedUser, residentRouter);
//app.use('/residents/edit', authUtils.permitAuthentiacatedUser, residentRouter);

//app.use('/reservedRooms/details', authUtils.permitAuthentiacatedUser, reservedRoomRouter);
//app.use('/reservedRooms/delete', authUtils.permitAuthentiacatedUser, reservedRoomRouter);

app.use('/', indexRouter);
app.use('/rooms', roomRouter);
app.use('/residents', residentRouter);
app.use('/reservedRooms', reservedRoomRouter);

app.use('/api/residents', resApiRouter);
app.use('/api/rooms', roomApiRouter);
app.use('/api/reservedRooms', reservedApiRouter);



// catch 404 and forward to error handler
app.use(function(req, res, next) {
    next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error');
});

module.exports = app;