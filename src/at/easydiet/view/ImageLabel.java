package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.Insets;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.media.Image;

/**
 * Renders an image and a lable aligned horizontally
 * @author Daniel
 * 
 */
public class ImageLabel extends TablePane
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ImageLabel.class);

    private ImageView                           _imageView;
    private Label                               _textLabel;
    
    

    /**
     * Gets the imageView.
     * @return the imageView
     */
    protected ImageView getImageView()
    {
        return _imageView;
    }

    /**
     * Gets the textLabel.
     * @return the textLabel
     */
    protected Label getTextLabel()
    {
        return _textLabel;
    }

    /**
     * @return
     * @see org.apache.pivot.wtk.ImageView#getImage()
     */
    public Image getImage()
    {
        return _imageView.getImage();
    }

    /**
     * @param image
     * @see org.apache.pivot.wtk.ImageView#setImage(org.apache.pivot.wtk.media.Image)
     */
    public void setImage(Image image)
    {
        _imageView.setImage(image);
    }

    /**
     * @param imageURL
     * @see org.apache.pivot.wtk.ImageView#setImage(java.net.URL)
     */
    public final void setImage(URL imageURL)
    {
        _imageView.setImage(imageURL);
    }

    /**
     * @param imageName
     * @see org.apache.pivot.wtk.ImageView#setImage(java.lang.String)
     */
    public final void setImage(String imageName)
    {
        _imageView.setImage(imageName);
    }

    /**
     * * Gets the image displayed within this label .
     * @param infoImage the infoImage to set
     */
    public void setInfoImage(ImageView infoImage)
    {
        _imageView = infoImage;
    }

    /**
     * @return
     * @see org.apache.pivot.wtk.Label#getText()
     */
    public String getText()
    {
        return _textLabel.getText();
    }

    /**
     * @param text
     * @see org.apache.pivot.wtk.Label#setText(java.lang.String)
     */
    public void setText(String text)
    {
        _textLabel.setText(text);
    }

    /**
     * Initializes a new instance of the {@link ImageLabel} class.
     */
    public ImageLabel()
    {
        getColumns().add(new Column(-1));
        getColumns().add(new Column(1, true));
        
        Row row = new Row();

        row.add(_imageView = new ImageView());
        row.add(_textLabel = new Label());

        getRows().add(row);
        
        _imageView.getStyles().put("verticalAlignment", VerticalAlignment.TOP);
        _imageView.getStyles().put("preserveAspectRatio", true);
        _textLabel.getStyles().put("verticalAlignment",
                VerticalAlignment.CENTER);
        _textLabel.getStyles().put("wrapText", true);

        getStyles().put("horizontalSpacing", 3);
        getStyles().put("padding", new Insets(2));

    }
}
