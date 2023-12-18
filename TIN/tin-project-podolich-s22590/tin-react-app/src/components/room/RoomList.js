import { Link } from 'react-router-dom'
import { getRoomsApiCall } from '../../apiCalls/roomApiCalls'

function RoomList() {
    const roomList = getRoomsApiCall()

    return (
<main>
        <h2>Rooms list</h2>
        <table className="table-list table-rooms">
            <thead>
                <tr>
                    <th>Room number</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
        <tbody>
            {roomList.map(room => (
            <tr key={room._id}>
            <td>{room.number}</td>
            <td>{room.price}</td>
            <td>
                <ul className="list-actions">
                    <li>
                        <Link to={`details/${room._id}`} className="list-actions-button-details-img">
                            <img alt="Details" src="../../images/details.png" width="24" height="24"/>
                        </Link>
                    <Link to={`details/${room._id}`} className="list-actions-button-details">Details</Link></li>
                    <li>
                        <Link to={`edit/${room._id}`} className="list-actions-button-edit-img">
                            <img alt="Details" src="../../images/edit.png" width="24" height="24"/>
                        </Link>
                        <Link to={`edit/${room._id}`} className="list-actions-button-edit">Edit</Link></li>
                    <li>
                        <Link to={`delete/${room._id}`} className="list-actions-button-delete-img">
                            <img alt="Details" src="../../images/delete.png" width="24" height="24"/>
                        </Link>
                        <Link to={`delete/${room._id}`} className="list-actions-button-delete">Delete</Link></li>
                </ul>
            </td>
        </tr>
    ))} 
            </tbody>
        </table>
        <p><Link to={`add/`} className="button-add">Add new room</Link></p>
    </main>
    )
}

export default RoomList