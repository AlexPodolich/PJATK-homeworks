import React from 'react'
import { Link, useParams } from 'react-router-dom'
import { getRoomByIdApiCall } from '../../apiCalls/roomApiCalls'
import { getFormattedDate } from '../../helpers/dateHelper'

function RoomDetails() {
    let { roomId } = useParams()
    roomId = parseInt(roomId)
    const room = getRoomByIdApiCall(roomId)

    return (
        <main>
            <h2>Room details</h2>
            <p>Room number: {room.number}</p>
            <p>Room price: {room.price} </p>
            <h2>Reserved room details</h2>
            <table className="table-list">
                <thead>
                    <tr>
                        <th>Surname</th>
                        <th>Start reservation date</th>
                        <th>End reservation date</th>
                    </tr>
                </thead>
                <tbody>
                    {room.reservedRooms.map(
                        reservedRoom =>
                            <tr key={reservedRoom._id}>
                                <td>{reservedRoom.resident.last_name}</td>
                                <td>{reservedRoom.start_date ? getFormattedDate(reservedRoom.start_date) : ""}</td>
                                <td>{reservedRoom.end_date ? getFormattedDate(reservedRoom.end_date) : ""}</td>
                            </tr>
                    )}
                </tbody>
            </table>
            <p>
                <Link to="/rooms" className="list-actions-button-details">Back</Link>
            </p>
        </main>
    )
}
export default RoomDetails