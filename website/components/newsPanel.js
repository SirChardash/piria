import useSWR from "swr";
import {Card, CardActions, CardContent, CardMedia, Grid} from "@mui/material";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Carousel from 'react-material-ui-carousel'
import styles from '../styles/newsPanel.module.css'
import {useRouter} from "next/router";
import fullL10n from "../l10n";
import fetcher from "../lib/fetch";
import Loading from "./loading";
import {useKeycloak} from "@react-keycloak/ssr";
import {logEvent} from "../lib/tracking";
import getUserId from "../lib/userId";

export default function NewsPanel({items}) {
    const {keycloak, initialized} = useKeycloak()

    if (!initialized) {
        return <Loading authenticated withLayout/>
    }

    const {locale} = useRouter()
    const l10n = fullL10n[locale].newsPanel

    const {
        data,
        error,
        isValidating
    } = useSWR('http://localhost:8081/news', url => fetcher(url, keycloak.token))


    return isValidating
        ? <Loading/>
        : error || !data
            ? <Grid py={5}/>
            : <Grid container justifyContent={'center'}>
                <Typography variant={'h5'}>{l10n.header}</Typography>
                <Carousel className={styles.container} interval={8000}>
                    {data.reduce((all, one, i) => groupIntoChunks(all, one, i, items), []).map(chunkDisplay)}
                </Carousel>
            </Grid>

}

function groupIntoChunks(all, one, i, items) {
    const ch = Math.floor(i / items);
    all[ch] = [].concat((all[ch] || []), one);
    return all
}

function chunkDisplay(chunk) {
    return (
        <Grid container justifyContent={'space-evenly'}>
            {chunk.map(entry => <NewsCard image={entry.imageUrl}
                                          title={entry.title}
                                          description={entry.description}
                                          articleUrl={entry.articleUrl}/>)}
        </Grid>
    )
}


function NewsCard({image, title, description, articleUrl}) {
    const {keycloak, initialized} = useKeycloak()

    return (
        <Card sx={{maxWidth: 345, height: 500}} className={styles.card}>
            <CardMedia
                component="img"
                height="140"
                image={image}
                alt={title}
            />
            <CardContent>
                <Typography gutterBottom variant="h6" component="div">{title}</Typography>
                <Typography variant="body2" color="text.secondary">{description}</Typography>
            </CardContent>
            <CardActions>
                <Button size="small" href={articleUrl} target={'_blank'} onClick={
                    () => logEvent(
                        initialized && keycloak.authenticated ? keycloak.idTokenParsed.sub : getUserId(),
                        'home page',
                        'news',
                        'article opened',
                        articleUrl
                    )
                }>See Full Article</Button>
            </CardActions>
        </Card>
    );
}