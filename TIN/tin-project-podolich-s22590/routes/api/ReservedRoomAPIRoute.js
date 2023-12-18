const express = require('express');
const router = express.Router();

const reservedRoomApiController = require('../../api/ReservedRoomAPI');

router.get('/', reservedRoomApiController.getReservedRooms);
router.get('/:reservedRoomId', reservedRoomApiController.getReservedRoomById);
router.post('/', reservedRoomApiController.createReservedRoom);
router.put('/:reservedRoomId', reservedRoomApiController.updateReservedRoom);
router.delete('/:reservedRoomId', reservedRoomApiController.deleteReservedRoom);

module.exports = router;