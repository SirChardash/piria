import Layout from "../components/layout";
import Typography from "@mui/material/Typography";

export default function Custom404() {
    return (
        <Layout>
                <Typography py={10} px={10} variant={'h5'} color={'text.secondary'}>
                    The page you're looking for was not found.
                </Typography>
        </Layout>
    )
}
