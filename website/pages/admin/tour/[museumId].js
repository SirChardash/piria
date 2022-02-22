import FullScreenAlert from "../../../components/fullScreenAlert";
import Layout from "../../../components/layout";

export default function NewTour(params) {
    return (
        <Layout admin>
            <FullScreenAlert title={'It is I ' + params.museumId} severity={'info'}>
                The frenchiest fry.
            </FullScreenAlert>
        </Layout>
    )
}

export async function getStaticProps({params}) {
    return {
        props: {
            museumId: params.museumId
        }
    }
}

export async function getStaticPaths() {
    return {
        paths: [
            {
                params: {
                    museumId: '1'
                }
            }
        ],
        fallback: true
    }
}