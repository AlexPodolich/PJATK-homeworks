const express = require('express');
const router = express.Router();
const authUtils = require('../utils/authUtils');

const roomController = require('../controllers/roomController');

router.get('/', authUtils.permitAuthentiacatedUser, roomController.showRoomList);

router.get('/add', authUtils.permitOnlyForAdmin, roomController.showAddRoomForm);

router.get('/edit/:roomId', authUtils.permitOnlyForAdmin, roomController.showEditRoomForm);

router.get('/details/:roomId', authUtils.permitOnlyForAdmin, roomController.showRoomDetails);

router.post('/add', authUtils.permitOnlyForAdmin, roomController.addRoom);

router.post('/edit', authUtils.permitOnlyForAdmin, roomController.updateRoom);

router.get('/delete/:roomId', authUtils.permitOnlyForAdmin, roomController.deleteRoom);

module.exports = router;