import { roomList, roomDetailsList } from './roomApiMockData'

export function getRoomsApiCall() {
    return roomList;
}

export function getRoomByIdApiCall(roomId) {
    const room = roomDetailsList.find(room => room._id === roomId)
    return room;
}