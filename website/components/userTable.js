import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import styles from '../styles/userTable.module.css'
import UserButton from "./userButton";

export default function UserTable({data}) {
    return (
        <TableContainer component={Paper}>
            <Table sx={{minWidth: 650}} aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell className={styles.header}>Username</TableCell>
                        <TableCell className={styles.header} align="right">First Name</TableCell>
                        <TableCell className={styles.header} align="right">Last Name</TableCell>
                        <TableCell className={styles.header} align="right">Email Address</TableCell>
                        <TableCell className={styles.header} align="right">Reset Password</TableCell>
                        <TableCell className={styles.header} align="right">Disable Login</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((user) => (
                        <TableRow key={user.username}
                                  sx={{'&:last-child td, &:last-child th': {border: 0}}}>
                            <TableCell component="th" scope="row">{user.username}</TableCell>
                            <TableCell align="right">{user.firstName}</TableCell>
                            <TableCell align="right">{user.lastName}</TableCell>
                            <TableCell align="right">{user.email}</TableCell>
                            <TableCell align="right">
                                <UserButton action={'resetPassword'} userId={user.id}>RESET</UserButton>
                            </TableCell>
                            <TableCell align="right">
                                <UserButton action={'disable'} userId={user.id}>DISABLE</UserButton>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    )
}