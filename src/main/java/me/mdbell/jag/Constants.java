package me.mdbell.jag;

/**
 * @author matt123337
 */
public interface Constants {

    String[] INDEX_NAMES = {"archives", "models", "anim", "audio", "maps"};
    String[] ARCHIVE_NAMES = {null, "title", "config", "interface", "media", "versionlist", "textures", "wordenc", "sounds"};

    int SECTOR_HEADER_SIZE = 8;

    int SECTOR_DATA_SIZE = 512;
    int SECTOR_TOTAL_SIZE = SECTOR_DATA_SIZE + SECTOR_HEADER_SIZE;

    int NULL_SECTOR_ID = 0;

    int INDEX_TOTAL_SIZE = 6;

    char[] BZIP_HEADER = {'B', 'Z', 'h', '1'};
    int HEADER_LENGTH = 4;
}
