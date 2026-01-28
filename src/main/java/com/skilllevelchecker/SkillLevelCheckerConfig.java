package com.skilllevelchecker;

import net.runelite.client.config.*;

import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.awt.*;

@ConfigGroup("example")
public interface SkillLevelCheckerConfig extends Config {
    @ConfigItem(
            keyName = "greeting",
            name = "Welcome Greeting",
            description = "The message to show to the user when they login"
    )
    default String greeting() {
        return "Hello";
    }

    @ConfigItem(
            keyName = "noRequirementsColor",
            name = "No requirements met color",
            description = "Color of the triangle when no requirements are met",
            position = 2,
            section = indicatorSection
    )
    default Color noRequirementsColor() {
        return new Color(255, 23, 23); // default red
    }

    @ConfigItem(
            keyName = "partialRequirementsColor",
            name = "Partial requirements met color",
            description = "Color of the triangle when some but not all requirements are met",
            position = 3,
            section = indicatorSection
    )
    default Color partialRequirementsColor() {
        return new Color(255, 206, 0); // default yellow
    }
    @ConfigItem(
            keyName = "triangleCorner",
            name = "Triangle corner",
            description = "Which corner to place the triangle indicator",
            position = 6,
            section = indicatorSection
    )
    default TriangleCorner triangleCorner()
    {
        return TriangleCorner.TOP_RIGHT;
    }
    @ConfigSection(
            name = "Indicator",
            description = "Settings related to the triangle indicator",
            position = 1
    )
    String indicatorSection = "indicatorSection";
    @Range(min = 10, max = 24)
    @ConfigItem(
            keyName = "tooltipTextSize",
            name = "Tooltip text size",
            description = "Font size for the requirements tooltip (points)",
            position = 1,
            section = tooltipSection
    )
    default int tooltipTextSize()
    {
        return 15;
    }
    @ConfigSection(
            name = "Tooltip",
            description = "Settings related to the tooltip",
            position = 0
    )
    String tooltipSection = "tooltipSection";
    @ConfigItem(
            keyName = "tooltipOpacityPercent",
            name = "Tooltip opacity",
            description = "Opacity of the tooltip background (0–100%)",
            position = 4,
            section = tooltipSection
    )
    default int tooltipOpacityPercent()
    {
        return 80;
    }
    @ConfigItem(
            keyName = "requirementsMetColor",
            name = "Requirements Met Color",
            description = "Color of the triangle when all requirements are met",
            position = 5,
            section =   indicatorSection
    )
    default Color requirementsMetColor()  {
        return new Color(0, 255, 0); // default green
    }
}
