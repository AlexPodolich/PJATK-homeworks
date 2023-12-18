const express = require('express');
const router = express.Router();
const authUtils = require('../utils/authUtils');

const reservedRoomController = require('../controllers/reservedRoomController');

router.get('/', authUtils.permitAuthentiacatedUser, reservedRoomController.showReservedRoomList);

router.get('/add', authUtils.permitAuthentiacatedUser, reservedRoomController.showAddReservedRoomForm);

router.get('/edit/:reservedRoomId', authUtils.permitOnlyForAdmin, reservedRoomController.showEditReservedRoomForm);

router.get('/details/:reservedRoomId', authUtils.checkReservedRoomId, reservedRoomController.showReservedRoomDetails);

router.post('/add', authUtils.permitAuthentiacatedUser, reservedRoomController.addReservedRoom);

router.post('/edit', authUtils.permitOnlyForAdmin, reservedRoomController.updateReservedRoom);

router.get('/delete/:reservedRoomId', authUtils.checkReservedRoomId, reservedRoomController.deleteReservedRoom);

module.exports = router;