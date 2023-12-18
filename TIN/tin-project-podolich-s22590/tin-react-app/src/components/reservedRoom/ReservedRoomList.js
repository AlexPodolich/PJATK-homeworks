import React from "react"
import { Link } from 'react-router-dom'
import { getReservedRoomsApiCall } from '../../apiCalls/reservedRoomApiCalls'

function ReservedRoomList() {
    const reservedRoomList = getReservedRoomsApiCall()

    return (
        <main>
            <h2>Reserved rooms list</h2>
            <table className="table-list table-resRooms">
                <thead>
                    <tr>
                        <th>Room number</th>
                        <th>Resident surname</th>
                        <th>Start reservation date</th>
                        <th>End reservation date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {reservedRoomList.map(reservedRoom => (
                        <tr key={reservedRoom._id}>
                            <td>{reservedRoom.room.number}</td>
                            <td>{reservedRoom.resident.last_name}</td>
                            <td>{reservedRoom.start_date}</td>
                            <td>{reservedRoom.end_date}</td>
                            <td>
                                <ul className="list-actions">
                                    <li>
                                        <Link to={`details/${reservedRoom._id}`} className="list-actions-button-details-img">
                                            <img alt="Details" src="../../images/details.png" width="24" height="24"/>
                                        </Link>
                                        <Link to={`details/${reservedRoom._id}`} className="list-actions-button-details">Details</Link></li>
                                    <li>
                                        <Link to={`edit/${reservedRoom._id}`} className="list-actions-button-edit-img">
                                            <img alt="Details" src="../../images/edit.png" width="24" height="24"/>
                                        </Link>
                                        <Link to={`edit/${reservedRoom._id}`} className="list-actions-button-edit">Edit</Link></li>
                                    <li>
                                        <Link to={`delete/${reservedRoom._id}`} className="list-actions-button-delete-img">
                                            <img alt="Details" src="../../images/delete.png" width="24" height="24"/>
                                        </Link>
                                        <Link to={`delete/${reservedRoom._id}`} className="list-actions-button-delete">Delete</Link></li>
                                </ul>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <p><Link to={`add/`} className="button-add">Add new reservation</Link></p>
        </main>
    )
}

export default ReservedRoomList