import {Card, CardActions, CardContent, Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Link from 'next/link';
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import {logEvent} from "../lib/tracking";
import getUserId from "../lib/userId";
import {useKeycloak} from "@react-keycloak/ssr";

export default function MuseumCard({data}) {
    const {locale} = useRouter()
    const l10n = fullL10n[locale].museumCard
    const {keycloak, initialized} = useKeycloak()

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
                    <Link href={'/museum/' + data.id}>
                        <a onClick={() => logEvent(
                            initialized && keycloak.authenticated ? keycloak.idTokenParsed.sub : getUserId(),
                            'museums',
                            'search results',
                            'museum page visit',
                            data.masterId
                        )}>
                            {l10n.seeMore}
                        </a>
                    </Link>
                </CardActions>
            </Card>
        </Box>
    )
}