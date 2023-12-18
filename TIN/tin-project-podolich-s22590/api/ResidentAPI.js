const ResidentRepository = require('../repository/mysql2/ResidentRepository');

exports.getResidents = (req, res, next) => {
    ResidentRepository.getResidents()
        .then(residents => {
            res.status(200).json(residents);
        })
        .catch(err => {
            console.error(err);
        });
};

exports.getResidentById = (req, res, next) => {
    const resId = req.params.resId;
    ResidentRepository.getResidentById(resId)
        .then(resident => {
            if (!resident) {
                res.status(404).json({
                    message: 'Resident with id ' + resId + ' does not exist'
                })
            } else {
                res.status(200).json(resident);
            }
        });
};

exports.createResident = (req, res, next) => {
    ResidentRepository.createResident(req.body)
        .then(newObj => {
            res.status(200).json(newObj);
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};

exports.updateResident = (req, res, next) => {
    const resId = req.params.resId;
    ResidentRepository.updateResident(resId, req.body)
        .then(result => {
            res.status(200).json({ message: 'Resident updated successfully!', resident: result });
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};

exports.deleteResident = (req, res, next) => {
    const resId = req.params.resId;
    ResidentRepository.deleteResident(resId)
        .then(result => {
            res.status(200).json({ message: 'Resident removed successfully!', resident: result });
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};