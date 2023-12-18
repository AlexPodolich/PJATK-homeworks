const express = require('express');
const router = express.Router();
const authUtils = require('../utils/authUtils');

const residentController = require('../controllers/residentController');

router.get('/', authUtils.permitAuthentiacatedUser, residentController.showResidentsList);

router.get('/add', authUtils.permitOnlyForAdmin, residentController.showAddResidentForm);

router.get('/edit/:resId', authUtils.checkLoggedIdUser, residentController.showEditResidentForm);

router.get('/details/:resId', authUtils.checkLoggedIdUser, residentController.showResidentDetails);

router.post('/add', authUtils.permitOnlyForAdmin, residentController.addResident);

router.post('/edit', authUtils.checkLoggedIdUser, residentController.updateResident);

router.get('/delete/:resId', authUtils.permitOnlyForAdmin, residentController.deleteResident);

module.exports = router;