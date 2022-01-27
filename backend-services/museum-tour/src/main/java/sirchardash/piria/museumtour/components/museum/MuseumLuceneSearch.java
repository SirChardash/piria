package sirchardash.piria.museumtour.components.museum;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.models.RefreshListener;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.apache.lucene.document.Field.Store.NO;

@Slf4j
public class MuseumLuceneSearch implements RefreshListener<Museum> {

    private static final String DESC = "description";
    private static final String ID = "id";

    private final int maxReturned;
    private final Directory memoryIndex;
    private final IndexWriter writer;
    private final StandardAnalyzer analyzer;
    private IndexSearcher search;
    private Map<Integer, Museum> museumMap = new HashMap<>();

    MuseumLuceneSearch(MuseumRefresh refresh,
                       Path luceneDirectoryPath,
                       int maxReturned) throws IOException {
        this.maxReturned = maxReturned;
        refresh.addRefreshListener(this);

        memoryIndex = new MMapDirectory(luceneDirectoryPath);
        analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(OpenMode.CREATE);
        writer = new IndexWriter(memoryIndex, indexWriterConfig);
        IndexReader indexReader = DirectoryReader.open(writer);
        search = new IndexSearcher(indexReader);
    }

    @Override
    public void onRefresh(Collection<Museum> newValues) {
        try {
            writer.deleteAll();
            writer.addDocuments(newValues.stream().map(MuseumLuceneSearch::toDoc).collect(Collectors.toList()));
            writer.commit();
            museumMap = newValues.stream().collect(Collectors.toMap(Museum::getId, museum -> museum));
            IndexReader indexReader = DirectoryReader.open(memoryIndex);
            search = new IndexSearcher(indexReader);
        } catch (IOException e) {
            log.warn("Failed to refresh search. Reason: ", e);
        }
    }

    public List<Museum> find(String query) {
        try {
            return Stream.of(search.search(query.isBlank()
                            ? new MatchAllDocsQuery()
                            : new QueryParser(DESC, analyzer).parse(query), maxReturned).scoreDocs)
                    .map(scoreDoc -> toMuseumId(scoreDoc, search))
                    .map(museumMap::get)
                    .collect(Collectors.toList());
        } catch (ParseException | IOException e) {
            log.warn("Failed to search for museums. Reason: ", e);
            return emptyList();
        }
    }

    private static Integer toMuseumId(ScoreDoc scoreDoc, IndexSearcher search) {
        try {
            return search.doc(scoreDoc.doc).getField("id").numericValue().intValue();
        } catch (IOException e) {
            log.warn("Failed to read doc with id {}. Reason", scoreDoc.doc, e);
            return null;
        }
    }

    private static Document toDoc(Museum museum) {
        Document doc = new Document();
        doc.add(new TextField(DESC, museum.getCity() + " " + museum.getName(), NO));
        doc.add(new StoredField(ID, museum.getId()));
        return doc;
    }
}
