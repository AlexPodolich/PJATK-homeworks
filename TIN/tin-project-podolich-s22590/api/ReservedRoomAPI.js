const ReservedRoomRepository = require('../repository/mysql2/ReservedRoomRepository');

exports.getReservedRooms = (req, res, next) => {
    ReservedRoomRepository.getReservedRooms()
        .then(reservedRoom => {
            res.status(200).json(reservedRoom);
        })
        .catch(err => {
            console.error(err);
        });
};

exports.getReservedRoomById = (req, res, next) => {
    const reservedRoomId = req.params.reservedRoomId;
    ReservedRoomRepository.getReservedRoomById(reservedRoomId)
        .then(reservedRoom => {
            if (!reservedRoom) {
                res.status(404).json({
                    message: 'Reserved room with id ' + reservedRoomId + ' does not exist'
                })
            } else {
                res.status(200).json(reservedRoom);
            }
        });
};

exports.createReservedRoom = (req, res, next) => {
    ReservedRoomRepository.createReservedRoom(req.body)
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

exports.updateReservedRoom = (req, res, next) => {
    const reservedRoomId = req.params.reservedRoomId;
    ReservedRoomRepository.updateReservedRoom(reservedRoomId, req.body)
        .then(result => {
            res.status(200).json({ message: 'Reserved room updated successfully!', reservedRoom: result });
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};

exports.deleteReservedRoom = (req, res, next) => {
    const reservedRoomId = req.params.reservedRoomId;
    ReservedRoom.deleteRoom(reservedRoomId)
        .then(result => {
            res.status(200).json({ message: 'Reserved room removed successfully!', reservedRoom: result });
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};