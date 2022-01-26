import {Card, CardActions, CardContent, Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Link from 'next/link';
import {useRouter} from "next/router";
import fullL10n from "../l10n";

export default function MuseumCard({data}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].museumCard

    return (
        <Box py={1}>
            <Card sx={{minWidth: 275}} variant={'outlined'}>
                <CardContent>

                    <Grid container justifyContent={'space-between'}>
                        <Grid item key={'country'}>
                            <Typography sx={{fontSize: 14}} color="text.secondary" gutterBottom>
                                {data.country}
                            </Typography>
                        </Grid>
                        <Grid item key={'type'}>
                            <Typography sx={{fontSize: 14}} color="text.secondary" gutterBottom>
                                {data.museumType}
                            </Typography>
                        </Grid>
                    </Grid>
                    <Typography variant="h5" component="div">
                        {data.name}
                    </Typography>
                    <Typography sx={{mb: 1.5}} color="text.secondary">
                        {data.address + ', ' + data.city}
                    </Typography>
                    <Typography sx={{mb: 1.5}} color="text.secondary">
                        {data.phoneNumber}
                    </Typography>
                </CardContent>
                <CardActions>
                    <Link href={'/museum/' + data.id}>{l10n.seeMore}</Link>
                </CardActions>
            </Card>
        </Box>
    )
}