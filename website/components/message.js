import Box from "@mui/material/Box";
import {Card, CardActions, CardContent, Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import ResponsiveButton from "./responsiveButton";

export default function Message({data}) {
    return (
        <Box py={1}>
            <Card sx={{minWidth: 275}} variant={'outlined'}>
                <CardContent>
                    <Grid container justifyContent={'space-between'}>
                        <Grid item key={'userId'}>
                            <Typography sx={{fontSize: 14}} color="text.secondary" gutterBottom>
                                {data.userId}
                            </Typography>
                        </Grid>
                        <Grid item key={'type'}>
                            <Typography sx={{fontSize: 14}} color="text.secondary" gutterBottom>
                                {data.contact}
                            </Typography>
                        </Grid>
                    </Grid>
                    <Typography component="div">
                        {data.content}
                    </Typography>
                </CardContent>
                <CardActions>
                    <ResponsiveButton endpoint={'http://localhost:8081/admin/message/' + data.id} method={'delete'}>
                        DELETE
                    </ResponsiveButton>
                </CardActions>
            </Card>
        </Box>
    )
}