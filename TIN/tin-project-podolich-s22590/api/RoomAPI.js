const RoomRepository = require('../repository/mysql2/RoomRepository');

exports.getRooms = (req, res, next) => {
    RoomRepository.getRooms()
        .then(room => {
            res.status(200).json(room);
        })
        .catch(err => {
            console.error(err);
        });
};

exports.getRoomById = (req, res, next) => {
    const roomId = req.params.roomId;
    RoomRepository.getRoomById(roomId)
        .then(room => {
            if (!room) {
                res.status(404).json({
                    message: 'Room with id ' + roomId + ' does not exist'
                })
            } else {
                res.status(200).json(room);
            }
        });
};

exports.createRoom = (req, res, next) => {
    RoomRepository.createRoom(req.body)
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

exports.updateRoom = (req, res, next) => {
    const roomId = req.params.roomId;
    RoomRepository.updateRoom(roomId, req.body)
        .then(result => {
            res.status(200).json({ message: 'Room updated successfully!', room: result });
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};

exports.deleteRoom = (req, res, next) => {
    const roomId = req.params.roomId;
    RoomRepository.deleteRoom(roomId)
        .then(result => {
            res.status(200).json({ message: 'Room removed successfully!', room: result });
        })
        .catch(err => {
            if (!err.statusCode) {
                err.statusCode = 500;
            }
            next(err);
        });
};