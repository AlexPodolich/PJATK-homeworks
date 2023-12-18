const ReservedRoomRepository = require('../repository/mysql2/ReservedRoomRepository');
const bcrypt = require('bcryptjs');

const salt = bcrypt.genSaltSync(8);

exports.hashPassword = (passPlain) => {
    const passHashed = bcrypt.hashSync(passPlain, salt);
    return passHashed;
}

exports.comparePasswords = (passPlain, passHash) => {
    const res = bcrypt.compareSync(passPlain, passHash);
    return res;
}

exports.permitAuthentiacatedUser = (req, res, next) => {
    const loggedUser = req.session.loggedUser;
    if (loggedUser) {
        next();
    } else {
        throw new Error('Unauthorized access!');
    }
}

exports.permitOnlyForAdmin = (req, res, next) => {
    const loggedUser = req.session.loggedUser;
    const admin = req.session.admin;
    if (admin) {
        next();
    }else if(loggedUser){
        throw new Error('Access only for admins!');
    } else {
        throw new Error('Unauthorized access!');
    }
}

exports.checkLoggedIdUser = (req, res, next) => {
    const loggedUser = req.session.loggedUser;
    console.log(loggedUser);
    const resIdParam = req.params.resId;
    const resIdBody = {...req.body };
    if (loggedUser._id == resIdParam || loggedUser._id == resIdBody) {
        next();
    } else {
        throw new Error('Unauthorized access!');
    }
}

exports.checkReservedRoomId = (req, res, next) => {
    const loggedUser = req.session.loggedUser;
    const resIdParam = req.params.reservedRoomId;

    // console.log(loggedUser._id);
    // console.log(resIdParam);
    ReservedRoomRepository.getReservedRoomById(resIdParam)
        .then(currRoom => {
            //console.log(currRoom.resident._id);
            if (currRoom.resident._id == loggedUser._id) {
                next();
            } else {
                throw new Error('Unauthorized access!');
            }
        }).catch(err =>{
        throw new Error('Unauthorized access!');
    });
}