import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import styles from "../styles/userTable.module.css";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function LogTable({data}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].stats

    return (
        <TableContainer component={Paper}>
            <Table sx={{minWidth: 650}} aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell className={styles.header}>{l10n.userId}</TableCell>
                        <TableCell className={styles.header}>{l10n.time}</TableCell>
                        <TableCell className={styles.header}>{l10n.category}</TableCell>
                        <TableCell className={styles.header}>{l10n.subcategory}</TableCell>
                        <TableCell className={styles.header}>{l10n.label}</TableCell>
                        <TableCell className={styles.header}>{l10n.value}</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((log) => (
                        <TableRow key={log.id}
                                  sx={{'&:last-child td, &:last-child th': {border: 0}}}>
                            <TableCell component="th" scope="row">{log.userId}</TableCell>
                            <TableCell>{log.time}</TableCell>
                            <TableCell>{log.category}</TableCell>
                            <TableCell>{log.subcategory}</TableCell>
                            <TableCell>{log.label}</TableCell>
                            <TableCell>{log.value}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    )
}