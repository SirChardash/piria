import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import styles from '../styles/userTable.module.css'
import ResponsiveButton from "./responsiveButton";
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import endpoints from "../endpoints";

export default function UserTable({data}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].users

    return (
        <TableContainer component={Paper}>
            <Table sx={{minWidth: 650}}>
                <TableHead>
                    <TableRow>
                        <TableCell className={styles.header}>{l10n.username}</TableCell>
                        <TableCell className={styles.header}>{l10n.firstName}</TableCell>
                        <TableCell className={styles.header}>{l10n.lastName}</TableCell>
                        <TableCell className={styles.header}>{l10n.email}</TableCell>
                        <TableCell className={styles.header}>{l10n.resetPassword}</TableCell>
                        <TableCell className={styles.header}>{l10n.disableLogin}</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((user) => (
                        <TableRow key={user.username}
                                  sx={{'&:last-child td, &:last-child th': {border: 0}}}>
                            <TableCell component="th" scope="row">{user.username}</TableCell>
                            <TableCell>{user.firstName}</TableCell>
                            <TableCell>{user.lastName}</TableCell>
                            <TableCell>{user.email}</TableCell>
                            <TableCell>
                                <ResponsiveButton
                                    action={endpoints.museumApp + '/admin/user/' + user.id + '/resetPassword'}>
                                    {l10n.resetPasswordButton}
                                </ResponsiveButton>
                            </TableCell>
                            <TableCell>
                                <ResponsiveButton
                                    action={endpoints.museumApp + '/admin/user/' + user.id + '/disable'}>
                                    {l10n.disableLoginButton}
                                </ResponsiveButton>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    )
}