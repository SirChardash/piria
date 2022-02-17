import Box from "@mui/material/Box";
import {Card, CardActions, CardContent, Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import ResponsiveButton from "./responsiveButton";
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function Message({data}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].messages

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
                        {l10n.delete}
                    </ResponsiveButton>
                </CardActions>
            </Card>
        </Box>
    )
}