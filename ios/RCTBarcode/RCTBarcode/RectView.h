
#import <UIKit/UIKit.h>

@interface RectView : UIView

@property (nonatomic,assign)CGRect scannerRect;
@property (nonatomic, copy) NSString *scannerRectCornerColor;
@property (nonatomic, copy) NSString *scannerText;

- (id)initWithScannerRect:(CGRect)scannerRect frame:(CGRect)frame scannerRectCornerColor:(NSString*)scannerRectCornerColor;

@end
