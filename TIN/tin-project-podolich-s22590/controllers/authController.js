const ResidentRepository = require('../repository/mysql2/ResidentRepository');
const authUtils = require('../utils/authUtils');

const adminPhone = "0675328180"
const adminPass = "admin";

exports.login = (req, res, next) => {
    const phone_number = req.body.phone_number;
    const password = req.body.password;
    ResidentRepository.findByPhone(phone_number)
        .then(resident => {
            if (!resident[0] && adminPhone != phone_number ) {
                res.render('index', {
                    navLocation: '',
                    loginError: req.__('validationMessage.loginError')
                })
            //} else if (resident[0].password === password) {
            } else if (authUtils.comparePasswords(password, resident[0].password)) {
                req.session.loggedUser = resident[0];
                if(adminPhone == phone_number && password == adminPass) {
                    req.session.admin = true;
                }else{
                    req.session.admin = false;
                }
                res.redirect('/');  
            }else {
                res.render('index', {
                    navLocation: '',
                    loginError: req.__('validationMessage.loginError')
                })
            }
        })
        .catch(err => {
            console.log(err);
        });

}

exports.logout = (req, res, next) => {
    req.session.loggedUser = undefined;
    req.session.admin = false;
    res.redirect('/');
}