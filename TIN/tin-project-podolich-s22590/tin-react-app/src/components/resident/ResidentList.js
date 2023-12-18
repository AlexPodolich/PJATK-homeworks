import { Link } from 'react-router-dom'
import { getResidentsApiCall } from '../../apiCalls/residentApiCalls'

function ResidentList() {
    const residentList = getResidentsApiCall()
    return (
        <main>
        <h2>Residents list</h2>
        <table className="table-list table-residents">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Phone number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            {residentList.map(res => (
        <tr key={res._id}>
            <td>{res.first_name}</td>
            <td>{res.last_name}</td>
            <td>{res.phone_number}</td>
            <td>
                <ul className="list-actions">
                    <li>
                        <Link to={`details/${res._id}`} className="list-actions-button-details-img">
                            <img alt="Details" src="../../images/details.png" width="24" height="24"/>
                        </Link>
                    <Link to={`details/${res._id}`} className="list-actions-button-details">Details</Link></li>
                    <li>
                        <Link to={`edit/${res._id}`} className="list-actions-button-edit-img">
                            <img alt="Details" src="../../images/edit.png" width="24" height="24"/>
                        </Link>
                        <Link to={`edit/${res._id}`} className="list-actions-button-edit">Edit</Link></li>
                    <li>
                        <Link to={`delete/${res._id}`} className="list-actions-button-delete-img">
                            <img alt="Details" src="../../images/delete.png" width="24" height="24"/>
                        </Link>
                        <Link to={`delete/${res._id}`} className="list-actions-button-delete">Delete</Link></li>
                </ul>
            </td>
        </tr>
    ))}
            </tbody>
        </table>
        <p>
            <Link to={`add/`} className="button-add">Add new resident</Link>
        </p>
    </main>
    )
}

export default ResidentList