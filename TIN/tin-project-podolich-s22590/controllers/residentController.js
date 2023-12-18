const ResidentRepository = require('../repository/mysql2/ResidentRepository');

exports.showResidentsList = (req, res, next) => {
    //res.render('pages/residents/list', { navLocation: 'resident' });
    ResidentRepository.getResidents()
        .then(residents => {
            res.render('pages/residents/list', {
                residents: residents,
                navLocation: 'resident',
                validationErrors: []
            });
        });
}

exports.showAddResidentForm = (req, res, next) => {
    //res.render('pages/residents/form', { navLocation: 'resident' });
    res.render('pages/residents/form', {
        resident: {},
        pageTitle: req.__('res.form.add.pageTitle'),
        formMode: 'createNew',
        btnLabel: req.__('res.form.add.btnLabel'),
        formAction: '/residents/add',
        navLocation: 'resident',
        validationErrors: []
    });
}

exports.showEditResidentForm = (req, res, next) => {
    const resId = req.params.resId;
    ResidentRepository.getResidentById(resId)
        .then(resident => {
            res.render('pages/residents/form', {
                resident: resident,
                pageTitle: req.__('res.form.edit.pageTitle'),
                formMode: 'edit',
                btnLabel: req.__('res.form.edit.btnLabel'),
                formAction: '/residents/edit',
                navLocation: 'resident',
                validationErrors: []
            });
        });
}

exports.showResidentDetails = (req, res, next) => {
    //res.render('pages/residents/details', { navLocation: 'resident' });
    const resId = req.params.resId;
    ResidentRepository.getResidentById(resId)
        .then(resident => {
            res.render('pages/residents/form', {
                resident: resident,
                pageTitle: req.__('res.form.details.pageTitle'),
                formMode: 'showDetails',
                formAction: '',
                navLocation: 'resident',
                validationErrors: []
            });
        });
}

exports.addResident = (req, res, next) => {
    const resData = {...req.body };
    ResidentRepository.createResident(resData)
        .then(result => {
            res.redirect('/residents');
        })
        .catch(err => {
            res.render('pages/residents/form', {
                resident: resData,
                pageTitle: req.__('res.form.add.pageTitle'),
                formMode: 'createNew',
                btnLabel: req.__('res.form.add.btnLabel'),
                formAction: '/residents/add',
                navLocation: 'resident',
                validationErrors: err.details
            });
        });
};

exports.updateResident = (req, res, next) => {
    const resId = req.body._id;
    const resData = {...req.body };
    ResidentRepository.updateResident(resId, resData)
        .then(result => {
            if(!req.session.admin){
                req.session.loggedUser = resData;
            }
            res.redirect('/residents');
        })
        .catch(err => {
            res.render('pages/residents/form', {
                resident: resData,
                pageTitle: req.__('res.form.edit.pageTitle'),
                formMode: 'edit',
                btnLabel: req.__('res.form.edit.btnLabel'),
                formAction: '/residents/edit',
                navLocation: 'resident',
                validationErrors: err.details
            });
        });
};

exports.deleteResident = (req, res, next) => {
    const resId = req.params.resId;
    ResidentRepository.deleteResident(resId)
        .then(() => {
            res.redirect('/residents');
        });
};