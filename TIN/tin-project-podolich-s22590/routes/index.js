var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { navLocation: 'main' });
});

//MP3
const AuthController = require('../controllers/authController');
const langController = require('../controllers/langController');
router.get('/changeLang/:lang', langController.changeLang);
router.post('/login', AuthController.login);
router.get('/logout', AuthController.logout);

module.exports = router;