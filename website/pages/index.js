import Head from 'next/head'
import Layout, {siteTitle} from '../components/layout'
import {Grid} from "@mui/material";
import Image from 'next/image';

export default function Home() {
    return (
        <Layout home>
            <Head>
                <title>{siteTitle}</title>
            </Head>
            <Grid container>
                <Grid item xs={0} md={8} px={3} py={3}>
                    This list of museums in Scotland contains museums which are defined for this context as institutions
                    (including nonprofit organisations, government entities, and private businesses) that collect and
                    care for objects of cultural, artistic, scientific, or historical interest and make their
                    collections or related exhibits available for public viewing. Also included are non-profit art
                    galleries and university art galleries. Museums that exist only in cyberspace (i.e., virtual
                    museums) are not included. Many other small historical displays are located in the country's stately
                    homes, castles and public libraries.
                </Grid>
                <Grid item xs={12} md={4} justifyContent={'center'}>
                    <Image src={'/museum1.jpg'} width={400} height={300}/>
                </Grid>
            </Grid>
            <Grid container py={10}>
                <Grid item xs={12} md={4} justifyContent={'center'}>
                    <Image src={'/museum2.jpg'} width={400} height={300}/>
                </Grid>
                <Grid item xs={12} md={8} px={3} py={3}>
                    This list of museums in Scotland contains museums which are defined for this context as institutions
                    (including nonprofit organisations, government entities, and private businesses) that collect and
                    care for objects of cultural, artistic, scientific, or historical interest and make their
                    collections or related exhibits available for public viewing. Also included are non-profit art
                    galleries and university art galleries. Museums that exist only in cyberspace (i.e., virtual
                    museums) are not included. Many other small historical displays are located in the country's stately
                    homes, castles and public libraries.
                </Grid>
            </Grid>
        </Layout>
    )
}
