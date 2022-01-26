import {Alert, AlertTitle, Grid} from "@mui/material";

export default function FullScreenAlert({severity, title, children}) {
    return (
        <Grid container justifyContent={'center'}>
            <Grid item xs={10} sm={5} py={6}>
                <Alert severity={severity}>
                    <AlertTitle>{title}</AlertTitle>
                    {children}
                </Alert>
            </Grid>
        </Grid>
    )
}