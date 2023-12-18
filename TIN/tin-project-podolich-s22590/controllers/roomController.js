const RoomRepository = require('../repository/mysql2/RoomRepository');

exports.showRoomList = (req, res, next) => {
    RoomRepository.getRooms()
        .then(rooms => {
            res.render('pages/rooms/list', {
                rooms: rooms,
                navLocation: 'room',
                validationErrors: []
            });
        });
}

exports.showAddRoomForm = (req, res, next) => {
    res.render('pages/rooms/form', {
        room: {},
        pageTitle: req.__('room.form.add.pageTitle'),
        formMode: 'createNew',
        btnLabel: req.__('room.form.add.btnLabel'),
        formAction: '/rooms/add',
        navLocation: 'room',
        validationErrors: []
    });
}

exports.showRoomDetails = (req, res, next) => {
    const roomId = req.params.roomId;
    RoomRepository.getRoomById(roomId)
        .then(room => {
            res.render('pages/rooms/form', {
                room: room,
                pageTitle: req.__('room.form.details.pageTitle'),
                formMode: 'showDetails',
                formAction: '',
                navLocation: 'room',
                validationErrors: []
            });
        });
}


exports.showEditRoomForm = (req, res, next) => {
    const roomId = req.params.roomId;
    RoomRepository.getRoomById(roomId)
        .then(room => {
            res.render('pages/rooms/form', {
                room: room,
                pageTitle: req.__('room.form.edit.pageTitle'),
                formMode: 'edit',
                btnLabel: req.__('room.form.edit.btnLabel'),
                formAction: '/rooms/edit',
                navLocation: 'room',
                validationErrors: []
            });
        });
}

exports.addRoom = (req, res, next) => {
    const roomData = {...req.body };
    RoomRepository.createRoom(roomData)
        .then(result => {
            res.redirect('/rooms');
        })
        .catch(err => {
            res.render('pages/rooms/form', {
                room: roomData,
                pageTitle: req.__('room.form.add.pageTitle'),
                formMode: 'createNew',
                btnLabel: req.__('room.form.add.btnLabel'),
                formAction: '/rooms/add',
                navLocation: 'room',
                validationErrors: err.details
            });
        });
};

exports.updateRoom = (req, res, next) => {
    const roomId = req.body._id;
    const roomData = {...req.body };
    RoomRepository.updateRoom(roomId, roomData)
        .then(result => {
            res.redirect('/rooms');
        })
        .catch(err => {
            res.render('pages/rooms/form', {
                room: roomData,
                pageTitle: req.__('room.form.edit.pageTitle'),
                formMode: 'edit',
                btnLabel: req.__('room.form.edit.btnLabel'),
                formAction: '/rooms/edit',
                navLocation: 'room',
                validationErrors: err.details
            });
        });
};

exports.deleteRoom = (req, res, next) => {
    const roomId = req.params.roomId;
    RoomRepository.deleteRoom(roomId)
        .then(() => {
            res.redirect('/rooms');
        });
};