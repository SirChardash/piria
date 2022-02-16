import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import styles from "../styles/userTable.module.css";

export default function LogTable({data}) {
    return (
        <TableContainer component={Paper}>
            <Table sx={{minWidth: 650}} aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell className={styles.header}>User ID</TableCell>
                        <TableCell className={styles.header}>Time</TableCell>
                        <TableCell className={styles.header}>Category</TableCell>
                        <TableCell className={styles.header}>Subcategory</TableCell>
                        <TableCell className={styles.header}>Label</TableCell>
                        <TableCell className={styles.header}>Value</TableCell>
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