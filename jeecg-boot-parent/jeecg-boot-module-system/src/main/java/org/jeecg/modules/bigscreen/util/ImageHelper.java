package org.jeecg.modules.bigscreen.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

@Slf4j
public class ImageHelper {
    /*
     * 根据尺寸图片居中裁剪
     */
    public static void cutCenterImage(String src, String dest, String suffix, int w, int h) throws IOException {
        Iterator iterator = ImageIO.getImageReadersByFormatName(suffix);
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        int imageIndex = 0;
        Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w, h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, suffix, new File(dest));

    }

    /*
     * 图片裁剪二分之一
     */
    public static void cutHalfImage(String src, String dest, String suffix) throws IOException {
        Iterator iterator = ImageIO.getImageReadersByFormatName(suffix);
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        int imageIndex = 0;
        int width = reader.getWidth(imageIndex) / 2;
        int height = reader.getHeight(imageIndex) / 2;
        Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, suffix, new File(dest));
    }
    /*
     * 图片裁剪通用接口
     */

    public static void cutImage(String src, String dest, String suffix, int x, int y, int w, int h) throws IOException {
        Iterator iterator = ImageIO.getImageReadersByFormatName(suffix);
        ImageReader reader = (ImageReader) iterator.next();
        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x, y, w, h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, suffix, new File(dest));
    }

    /*
     * 图片缩放
     */
    public static void zoomImage(String src, String dest, int w, int h) throws Exception {
        double wr = 0, hr = 0;
        File srcFile = new File(src);
        File destFile = new File(dest);
        BufferedImage bufImg = ImageIO.read(srcFile);
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
        wr = w * 1.0 / bufImg.getWidth();
        hr = h * 1.0 / bufImg.getHeight();
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int[] getWidthAndHeight(File file){
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            BufferedImage bi = ImageIO.read(in);// 通过ImageIO读取输入流来获取一个BufferedImage对象
            int height = bi.getHeight();//获取高度
            int width = bi.getWidth();//获取宽度

            return new int[]{width,height};
        } catch (FileNotFoundException e) {
            log.error("getWidthAndHeight error,",e);
        } catch (IOException e) {
            log.error("getWidthAndHeight error,",e);
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}  
