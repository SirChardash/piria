import {Container, Grid, Link} from "@mui/material";
import Box from "@mui/material/Box";
import styles from '../styles/footer.module.css';
import Image from 'next/image'

export default function Footer() {
    return (
        <Box className={styles.footer}>
            <Container maxWidth='lg'>
                <Grid container px={{xs: 3, sm: 10}} py={{xs: 3, sm: 5}}>
                    <Grid item xs={12} sm={4}>
                        <Box px={{xs: 0, sm: 4}}>
                            <strong>BRAVESMART</strong>
                            <p>
                                Made as an assigment for the college. I'm not even Scottish.
                            </p>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <Box px={{xs: 0, sm: 4}}>
                            <strong>ABOUT US</strong>
                            <p>BraveSmart is an entrypoint to Scottish culture and heritage. Discover museums and
                                galleries from your Corona-safe living room. To be brave, first you need to be smart.
                                Otherwise, you're just a full rushing to their end. Also, it's a pun.</p>
                        </Box>
                    </Grid>
                    <Grid item xs={12} sm={3}>
                        <Box px={{xs: 0, sm: 4}}>
                            <strong>CONTACT</strong>
                            <ul>
                                <li>
                                    Find the author on <Link href={'https://github.com/SirChardash'}
                                                             target={'_blank'}>GitHub</Link>
                                </li>
                                <li>
                                    Contact the <Link href={'https://etf.unibl.org/'} target={'_blank'}>Faculty</Link>
                                </li>
                                <li>
                                    I'm not giving you my phone number
                                </li>
                            </ul>
                        </Box>
                    </Grid>
                    <Grid justifyContent={'space-evenly'}>
                        <Image
                            priority
                            src="/flag-english.png"
                            className={styles.round}
                            height={32}
                            width={32}
                        />
                        {' '}
                        <Image
                            src="/flag-serbian.png"
                            className={styles.round}
                            height={32}
                            width={32}
                        />
                    </Grid>
                </Grid>
            </Container>
        </Box>
    )
}