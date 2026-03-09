public class TextEntity {
    private String content;
    private String language;
    private int id;



    public TextEntity(String content, String language, int id)
    {
        this.content = content;
        this.language = language;
        this.id = id;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (! (obj instanceof TextEntity))
            return false;

        TextEntity cpy = (TextEntity) obj;

        if (cpy.content == content && cpy.language == language)
            return true;

        return false;
    }

    public String toString()
    {
        return getClass().getName()+"{id="+id+", language='"+language+"', content='"+content+"'}";
    }

    public String getContent()
    {
        return content;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = content.hashCode()* 31 + language.hashCode()*31;
        return hash;
    }

    public Object clone()
    {
        TextEntity obj = new TextEntity(content, language, id);
        return obj;
    }
}
